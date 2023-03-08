package study.project.domain.item.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.project.domain.item.Item;
import study.project.domain.item.QItem;
import study.project.domain.item.search.ItemSearch;
import study.project.web.item.dto.MemberItemDto;
import study.project.web.item.dto.QMemberItemDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static study.project.domain.item.QItem.*;
import static study.project.domain.member.QMember.*;


@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MemberItemDto> myItemList(Long memberId) {
        return queryFactory.select(new QMemberItemDto(
                member.loginId,
                        item.id,
                        item.itemName,
                        item.stockQuantity,
                        item.createItemTime,
                        item.price
                ))
                .from(item)
                .leftJoin(item.member, member)
                .where(item.member.id.eq(memberId))
                .fetch();
    }
    @Override
    public List<Item> findItemPaging(int startIndex, int pageSize){
        return em.createQuery("select i from Item i order by i.createItemTime desc ",Item.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<MemberItemDto> myItemListPaging(Long memberId, int startIndex, int pageSize) {

        return queryFactory.select(new QMemberItemDto(
                member.loginId,
                        item.id,
                        item.itemName,
                        item.stockQuantity,
                        item.createItemTime,
                        item.price))
                .from(item)
                .leftJoin(item.member, member)
                .where(item.member.id.eq(memberId))
                .orderBy(item.createItemTime.desc())
                .offset(startIndex)
                .limit(pageSize)
                .fetch();
    }


    @Override
    public List<Item> itemSearchPaging(ItemSearch itemSearch , int startIndex, int pageSize) {

        return queryFactory.selectFrom(item)
                .where(itemNameContain(itemSearch.getItemName()))
                .orderBy(item.createItemTime.desc())
                .offset(startIndex) // 시작 인덱스
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Item> itemSearchPaging(ItemSearch itemSearch) {
        return queryFactory.selectFrom(item)
                .where(itemNameContain(itemSearch.getItemName()))
                .orderBy(item.createItemTime.desc())
                .fetch();
    }

    private BooleanExpression itemNameContain(String itemName) {
        if (itemName == null){
            return null;
        }
        return item.itemName.contains(itemName);
    }

}

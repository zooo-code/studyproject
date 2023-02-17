package study.project.domain.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.project.web.item.dto.MemberItemDto;
import study.project.web.item.dto.QMemberItemDto;

import java.util.List;

import static study.project.domain.item.QItem.*;
import static study.project.domain.member.QMember.*;


@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberItemDto> myItemList(Long memberId) {
        return queryFactory.select(new QMemberItemDto(
                member.loginId,
                        item.id,
                        item.itemName,
                        item.stockQuantity,
                        item.price
                ))
                .from(item)
                .leftJoin(item.member, member)
                .where(item.member.id.eq(memberId))
                .fetch();
    }
}

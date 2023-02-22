package study.project.domain.order.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import study.project.domain.member.QMember;
import study.project.domain.order.Order;

import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.dto.QMemberOrderDto;

import java.util.List;

import static study.project.domain.member.QMember.*;
import static study.project.domain.order.QOrder.*;
import static study.project.domain.order.QOrderItem.*;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{


    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberOrderDto> myOrderList(Long memberId) {
        return queryFactory.select(new QMemberOrderDto(
                        order.id,
                order.member.id,
                orderItem.item.itemName,
                orderItem.count,
                        orderItem.orderPrice,
                order.orderDate
                        ,order.status
                ))
                .from(orderItem)
                .leftJoin(orderItem.order,order)
                .where(order.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Order> findAllWithMember( ) {
        return queryFactory.selectFrom(order)
                .join(order.member,member).fetchJoin()
                .fetch();
    }

    @Override
    public List<Order> findAllWithItem() {
        queryFactory.selectDistinct()
                .from(order)
                .join(order.member).join(member)
                .join(order.orderItems)
                .join(orderItem.item)
                .fetchJoin().fetch();

    }


}

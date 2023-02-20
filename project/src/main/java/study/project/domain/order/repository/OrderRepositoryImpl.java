package study.project.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.project.domain.order.QOrder;
import study.project.domain.order.QOrderItem;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.dto.QMemberOrderDto;

import java.util.List;

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
                orderItem.count, orderItem.orderPrice,
                order.status
                ))
                .from(orderItem)
                .leftJoin(orderItem.order,order)
                .where(order.member.id.eq(memberId))
                .fetch();
    }
}

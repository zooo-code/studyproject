package study.project.domain.order.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.project.domain.item.QItem;
import study.project.domain.member.QMember;
import study.project.domain.order.Order;

import study.project.domain.order.dto.CustomerOrderList;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.dto.QCustomerOrderList;
import study.project.domain.order.dto.QMemberOrderDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static study.project.domain.member.QMember.*;
import static study.project.domain.order.QOrder.*;
import static study.project.domain.order.QOrderItem.*;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberOrderDto> myOrderList(Long memberId) {
        return queryFactory.select(new QMemberOrderDto(
                        order.id,
                order.member.id,
                orderItem.item.itemName,
                orderItem.count,
                        orderItem.orderPrice,
                order.orderDate,
                        order.status,
                order.delivery
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
        return em.createQuery("select distinct o from Order o" +
                " join fetch o.member m" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item i",Order.class)
                .getResultList();
    }

    @Override
    public List<Order> findAllWithMember(int offset, int limit) {
        return em.createQuery("select o from Order o" +
                        " join fetch o.member m",Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<MemberOrderDto> myOrderListPaging(Long memberId,int startIndex, int pageSize) {
        return queryFactory.select(new QMemberOrderDto(
                        order.id,
                        order.member.id,
                        orderItem.item.itemName,
                        orderItem.count,
                        orderItem.orderPrice,
                        order.orderDate,
                        order.status,
                        order.delivery
                ))
                .from(orderItem)
                .leftJoin(orderItem.order,order)
                .where(order.member.id.eq(memberId))
                .orderBy(order.orderDate.desc())
                .offset(startIndex)
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<CustomerOrderList> customerOrderList(Long memberId) {
        return queryFactory.select(new QCustomerOrderList(
                    order.id,
    //                내 아이디
                    QItem.item.member.id,
    //                주문 고객 아이디
                    order.member.loginId,
                    orderItem.item.itemName,
                    orderItem.count,
                    orderItem.orderPrice,
                    order.orderDate,
                        order.status,
                    order.delivery
                    ))
                    .from(orderItem)
                .leftJoin(orderItem.order,order)
                .leftJoin(orderItem.item,QItem.item)
                .where(QItem.item.member.id.eq(memberId))
                .orderBy(order.orderDate.desc())
                .fetch();
    }

    @Override
    public List<CustomerOrderList> customerOrderListPaging(Long memberId, int startIndex, int pageSize) {
        return queryFactory.select(new QCustomerOrderList(
                        order.id,
                        //                내 아이디
                        QItem.item.member.id,
                        //                주문 고객 아이디
                        order.member.loginId,
                        orderItem.item.itemName,
                        orderItem.count,
                        orderItem.orderPrice,
                        order.orderDate,
                        order.status,
                        order.delivery
                ))
                .from(orderItem)
                .leftJoin(orderItem.order,order)
                .leftJoin(orderItem.item,QItem.item)
                .where(QItem.item.member.id.eq(memberId))
                .orderBy(order.orderDate.desc())
                .offset(startIndex)
                .limit(pageSize)
                .fetch();
    }


}

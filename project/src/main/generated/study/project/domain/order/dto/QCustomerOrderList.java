package study.project.domain.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * study.project.domain.order.dto.QCustomerOrderList is a Querydsl Projection type for CustomerOrderList
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCustomerOrderList extends ConstructorExpression<CustomerOrderList> {

    private static final long serialVersionUID = 774248399L;

    public QCustomerOrderList(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> customerId, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> buyCount, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderTime, com.querydsl.core.types.Expression<study.project.domain.order.OrderStatus> status, com.querydsl.core.types.Expression<? extends study.project.domain.delivery.Delivery> delivery) {
        super(CustomerOrderList.class, new Class<?>[]{long.class, long.class, String.class, String.class, int.class, int.class, java.time.LocalDateTime.class, study.project.domain.order.OrderStatus.class, study.project.domain.delivery.Delivery.class}, orderId, memberId, customerId, itemName, buyCount, totalPrice, orderTime, status, delivery);
    }

}


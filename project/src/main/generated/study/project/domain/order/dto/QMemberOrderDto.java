package study.project.domain.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * study.project.domain.order.dto.QMemberOrderDto is a Querydsl Projection type for MemberOrderDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberOrderDto extends ConstructorExpression<MemberOrderDto> {

    private static final long serialVersionUID = -843993334L;

    public QMemberOrderDto(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> buyCount, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderTime, com.querydsl.core.types.Expression<study.project.domain.order.OrderStatus> status) {
        super(MemberOrderDto.class, new Class<?>[]{long.class, long.class, String.class, int.class, int.class, java.time.LocalDateTime.class, study.project.domain.order.OrderStatus.class}, orderId, memberId, itemName, buyCount, totalPrice, orderTime, status);
    }

}


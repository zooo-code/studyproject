package study.project.domain.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import study.project.domain.order.OrderStatus;

import java.time.LocalDateTime;

@Getter
public class MemberOrderDto {
    private Long orderId;
    private Long memberId;
    private String itemName;
    private int buyCount;
    private int totalPrice;

    private LocalDateTime orderTime;
    private OrderStatus status;
    @QueryProjection
    public MemberOrderDto(Long orderId, Long memberId, String itemName, int buyCount, int totalPrice, LocalDateTime orderTime, OrderStatus status) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.itemName = itemName;
        this.buyCount = buyCount;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.status = status;
    }
}

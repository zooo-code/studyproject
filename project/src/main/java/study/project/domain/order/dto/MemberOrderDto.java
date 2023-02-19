package study.project.domain.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberOrderDto {
    private Long orderId;
    private Long memberId;
    private String itemName;
    private int buyCount;
    private int totalPrice;

    @QueryProjection
    public MemberOrderDto(Long orderId, Long memberId, String itemName, int buyCount, int totalPrice) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.itemName = itemName;
        this.buyCount = buyCount;
        this.totalPrice = totalPrice;
    }
}

package study.project.api.order.dto;

import lombok.Data;
import study.project.domain.order.OrderItem;

@Data
public class OrderItemDto {
    private String itemName;//상품 명
    private int orderPrice; //주문 가격
    private int count; //주문 수량
    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getItemName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}

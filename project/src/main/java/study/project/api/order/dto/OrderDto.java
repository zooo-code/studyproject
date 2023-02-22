package study.project.api.order.dto;


import lombok.Data;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;
import study.project.domain.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getUsername();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(Collectors.toList());
    }
}

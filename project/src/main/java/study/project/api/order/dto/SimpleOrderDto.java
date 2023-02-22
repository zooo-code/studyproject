package study.project.api.order.dto;

import lombok.Data;
import study.project.domain.order.Order;
import study.project.domain.order.OrderStatus;
import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getUsername();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();

    }
}

package study.project.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    OrderItem findByOrderId(Long OrderId);
}

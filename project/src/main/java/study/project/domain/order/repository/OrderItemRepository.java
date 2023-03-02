package study.project.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.project.domain.order.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    OrderItem findByOrderId(Long OrderId);
    List<OrderItem> findByItemId(Long itemId);
}

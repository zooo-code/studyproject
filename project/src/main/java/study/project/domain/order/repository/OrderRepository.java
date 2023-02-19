package study.project.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.order.Order;


public interface OrderRepository extends JpaRepository<Order,Long>, OrderRepositoryCustom {
}

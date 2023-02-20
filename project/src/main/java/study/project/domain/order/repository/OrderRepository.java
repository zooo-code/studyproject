package study.project.domain.order.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.member.Member;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long>, OrderRepositoryCustom {
    List<Order> findByMemberId(Long memberId);
}

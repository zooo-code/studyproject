package study.project.domain.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.project.domain.order.Order;
import study.project.domain.order.dto.MemberOrderDto;
import java.util.List;


public interface OrderService {
    Long order(Long memberId, Long itemId, int count);
    void cancelOrder(Long orderId);

    Order findById(Long orderId);

    List<MemberOrderDto> findMyOrderItems(Long memberId);

    List<Long> findByMemberId(Long memberId);

    List<Order> finaAll();

    List<Order> findAllWithMember();


}

package study.project.domain.order.service;


import org.springframework.stereotype.Service;


public interface OrderService {
    Long order(Long memberId, Long itemId, int count);
    void cancelOrder(Long orderId);


}

package jpa.jpashop.domain.order.service;

import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;

import java.util.List;

public interface OrderService {

    Long order(Long memberId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);
}

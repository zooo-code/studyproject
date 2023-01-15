package jpa.jpashop.domain.order.repository;


import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;

import java.util.List;


public interface OrderRepository {



    void save(Order order);

    Order findOne(Long id);

    List<Order> findAll();

    List<Order> findAllByString(OrderSearch orderSearch);

    List<Order> findAllWithMemberDelivery();
}

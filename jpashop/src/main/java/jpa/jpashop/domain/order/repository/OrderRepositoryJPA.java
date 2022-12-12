package jpa.jpashop.domain.order.repository;


import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryJPA implements OrderRepository{

    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        return null;
    }
}

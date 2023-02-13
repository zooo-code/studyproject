package jpa.jpashop.domain.order.service;

import jpa.jpashop.domain.delivery.Address;
import jpa.jpashop.domain.exception.NotEnoughStockException;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.itemcategory.Book;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderStatus;
import jpa.jpashop.domain.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class OrderServiceJPATest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderServiceJPA orderServiceJPA;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        //When
        Long orderId = orderServiceJPA.order(member.getId(), item.getId(), orderCount);
        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals( 10000 * 2, getOrder.getTotalPrice());
        assertEquals(8, item.getStockQuantity());
    }
    @Test
    public void 상품주문_재고수량초과() {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 11; //재고보다 많은 수량
        //When

        //Then
        Assertions.assertThatThrownBy(() -> orderServiceJPA.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);
    }
    @Test
    public void 주문취소() {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        Long orderId = orderServiceJPA.order(member.getId(), item.getId(),
                orderCount);
        //When
        orderServiceJPA.cancelOrder(orderId);
        //Then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals( 10, item.getStockQuantity());
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}
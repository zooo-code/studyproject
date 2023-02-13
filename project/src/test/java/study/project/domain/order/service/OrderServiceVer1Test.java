package study.project.domain.order.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.exception.NotEnoughStockException;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.domain.order.Order;
import study.project.domain.order.OrderStatus;
import study.project.domain.order.repository.OrderRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class OrderServiceVer1Test {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Test
    @Rollback(value = false)
    public void 상품주문() {
        //given
        Member member1 = new Member("test","test1", "123");
        Member member2 = new Member("test1", "test1", "123");
        memberService.join(member1);
        memberService.join(member2);
        Item testItem = new Item(member1, "testItem", 10, 1000);
        itemService.saveItem(testItem);

        //when
        Long orderId = orderService.order(member2.getId(), testItem.getId(), 4);
        Order getOrder = orderRepository.findById(orderId).get();


        //then
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(1000*4);
        assertThat(testItem.getStockQuantity()).isEqualTo(6);
    }

    @Test
    public void 상품주문재고수량초과() {
        //given
        Member member = new Member("test","test1", "123");
        Member member1 = memberService.join(member);

        Item testItem = new Item(member1, "testItem", 10, 1000);
        Item item = itemService.saveItem(testItem);
        //when
        int orderCount = 11; //재고보다 많은 수량

        Assertions.assertThatThrownBy(()-> orderService.order(member1.getId(),item.getId(),orderCount))
                .isInstanceOf(NotEnoughStockException.class);

        //then
//        Assertions.assertThatThrownBy(() -> orderService.order(member1.getId(), item.getId(), orderCount))
//                .isInstanceOf(NotEnoughStockException.class);
    }
    @Test
    @Rollback(value = false)
    public void 주문취소() {
        //given
        Member member = new Member("test","test1", "123");
        Member member1 = memberService.join(member);

        Item testItem = new Item(member1, "testItem", 10, 1000);
        Item item = itemService.saveItem(testItem);
        int orderCount = 2;
        //when
        Long orderId = orderService.order(member1.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);
        //then
        Optional<Order> byId = orderRepository.findById(orderId);
        assertEquals(OrderStatus.CANCEL, byId.get().getStatus());
        assertEquals(10,item.getStockQuantity());
    }


}
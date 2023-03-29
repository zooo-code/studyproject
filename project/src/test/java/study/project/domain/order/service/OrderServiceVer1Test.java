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
import study.project.domain.order.OrderItem;
import study.project.domain.order.OrderStatus;
import study.project.domain.order.dto.CustomerOrderList;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.repository.OrderRepository;

import java.util.List;
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
        Member member1Join = memberService.join(member1);
        Member member2Join = memberService.join(member2);
        Item testItem = new Item(member1Join, "testItem", 10, 1000);
        Item item = itemService.saveItem(testItem);

        //when
        Long order = orderService.order(member2Join.getId(), item.getId(), 3);
        Order findOrder = orderService.findById(order);

        //then
        Assertions.assertThat(findOrder.getId()).isEqualTo(order);
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


        //then
        Assertions.assertThatThrownBy(()-> orderService.order(member1.getId(),item.getId(),orderCount))
                .isInstanceOf(NotEnoughStockException.class);
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

    @Test
    public void 주문확인() {
        //given
        Member member = new Member("test","test1", "123");
        Member member1 = memberService.join(member);
        Item testItem = new Item(member1, "testItem", 10, 1000);
        Item item = itemService.saveItem(testItem);
        Member member2 = new Member("test1", "test1", "123");
        Member member2Join = memberService.join(member2);

        //when
        int orderCount = 3;
        Long orderId = orderService.order(member1.getId(), item.getId(), orderCount);
        Order order = orderService.findById(orderId);
        Long order1 = orderService.order(member1.getId(), item.getId(), orderCount);
        List<Order> byMemberId = orderRepository.findByMemberId(member1.getId());
        Long order2 = orderService.order(member2Join.getId(), item.getId(), orderCount);
        Order byId = orderService.findById(order2);
        List<Order> byMemberId1 = orderRepository.findByMemberId(member2Join.getId());
        //then
        boolean contains = byMemberId.contains(order);
        Assertions.assertThat(contains).isTrue();
//        member1 이 주문한거 포함하면 안됨
        boolean contains1 = byMemberId1.contains(order);
        Assertions.assertThat(contains1).isFalse();
    }

}
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
        memberService.join(member1);
        Member join = memberService.join(member2);
        Item testItem = new Item(member1, "testItem", 10, 1000);
        itemService.saveItem(testItem);

        //when
        Long orderId = orderService.order(member2.getId(), testItem.getId(), 4);
        Long orderId2 = orderService.order(member2.getId(), testItem.getId(), 2);
        List<Long> byMemberId = orderService.findByMemberId(join.getId());
        for (Long order : byMemberId) {
            System.out.println("order.getId() = " + order);
        }
        System.out.println("orderId = " + orderId);
        System.out.println("orderId2 = " + orderId2);
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

    @Test
    public void myOrderList() {


        //given
        List<MemberOrderDto> memberOrderDtos = orderService.myOrderListPaging(1L, 0, 10);

        //when
        for (MemberOrderDto memberOrderDto : memberOrderDtos) {
            System.out.println("memberOrderDto = " + memberOrderDto.getOrderTime());
        }
        //then
    }
    @Test
    public void customerOrderList() {
        //given
        List<CustomerOrderList> customerOrderLists = orderService.customerOrderListPaging(1L, 0, 10);
        //when
        //then
        List<Long> longs = orderService.customerOrderId(1L);
        for (Long aLong : longs) {
            System.out.println("aLong = " + aLong);
        }
    }
}
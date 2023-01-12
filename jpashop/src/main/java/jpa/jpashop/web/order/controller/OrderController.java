package jpa.jpashop.web.order.controller;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.service.ItemService;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.service.MemberService;
import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;
import jpa.jpashop.domain.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
@Slf4j
//@Controller
@RequiredArgsConstructor
//@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }


    @PostMapping
    @Validated
    public String order(@RequestParam("memberId") @NotNull Long memberId,
                        @RequestParam("itemId") @NotNull Long itemId,
                        @RequestParam("count")  @NotNull @Min(value = 1,message = "최소 1 이상이어야 합니다." ) int count
                        ) {

        log.info("memberId = {}, itemId = {}, count = {}",memberId,itemId,count);
        orderService.order(memberId, itemId, count);
        return "redirect:/order/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/order/orders";
    }

}

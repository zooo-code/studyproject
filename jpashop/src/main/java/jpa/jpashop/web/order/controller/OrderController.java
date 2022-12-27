package jpa.jpashop.web.order.controller;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.service.ItemService;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.service.MemberService;
import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;
import jpa.jpashop.domain.order.service.OrderService;
import jpa.jpashop.web.order.form.OrderForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Slf4j
@Controller
@Validated
@RequiredArgsConstructor
@RequestMapping("/order")
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
    public String order(@RequestParam("memberId")  Long memberId,
                        @RequestParam("itemId")  Long itemId,
                        @RequestParam("count")  @Min(value = 1) int count,
                        BindingResult result) {

        if (result.hasErrors()){
            log.info("errors={} ", result);
            return "order/orderForm";
        }
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

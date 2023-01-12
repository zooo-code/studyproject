package jpa.jpashop.web.order.controller;

import jpa.jpashop.argumentResolver.Login;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.service.ItemService;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.service.MemberService;
import jpa.jpashop.domain.order.Order;
import jpa.jpashop.domain.order.OrderSearch;
import jpa.jpashop.domain.order.service.OrderService;
import jpa.jpashop.web.item.form.ItemForm;
import jpa.jpashop.web.order.form.OrderForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderMemberController {

    private final OrderService orderService;

    private final ItemService itemService;

    @GetMapping("/items/{itemId}/order")
    public String createForm(@PathVariable("itemId") Long itemId, @Login Member loginMember, Model model ) {

        if (loginMember == null) {
            log.info("home not loginMember");
            return "home";
        }

        Item item = itemService.findOne(itemId);
        OrderForm form = new OrderForm();
        form.setMemberId(loginMember.getId());

        form.setItemId(itemId);
        form.setItemName(item.getName());
        form.setItemPrice(item.getPrice());

        model.addAttribute("loginMember",loginMember);
        model.addAttribute("form",form);

        return "order/orderFormMember";
    }

    @PostMapping("/items/{itemId}/order")
    public String order(@PathVariable Long itemId ,
                        @Validated @ModelAttribute("loginMember") Member loginMember,
                        @Validated @ModelAttribute("form") OrderForm form,
                        BindingResult result){

        if (result.hasErrors()){
            return "order/orderFormMember";
        }
        System.out.println(form.getItemName());
        form.setItemId(itemId);
        System.out.println(loginMember.getAddress().getCity());
        System.out.println(loginMember.getId());
        orderService.order(form.getMemberId(), form.getItemId(), form.getCount());
        return "redirect:/order/{itemId}";
    }

    @GetMapping(value = "/order/{itemId}")
    public String orderItem(@PathVariable Long itemId) {

        return "order/orderList";
    }

    @GetMapping(value = "/order/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

}

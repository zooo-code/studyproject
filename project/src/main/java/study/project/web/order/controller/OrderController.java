package study.project.web.order.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.service.OrderItemService;
import study.project.domain.order.service.OrderService;
import study.project.web.argumentResolver.Login;
import study.project.web.order.form.OrderForm;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    @GetMapping("/{itemId}/{memberId}")
    public String order(@Login Member loginMember,
                        @PathVariable Long itemId, @PathVariable Long memberId, Model model){
        Item item = itemService.findByIdItem(itemId).get();
        OrderForm orderForm = new OrderForm(loginMember.getLoginId(),itemId,
                item.getItemName(),item.getPrice(),0);

        model.addAttribute("member",loginMember);
        model.addAttribute("order",orderForm);
        log.info("getOrderForm");
        return "/items/order/orderForm" ;
    }
    @PostMapping("/{itemId}/{memberId}")
    public String orderItem(@ModelAttribute("member") @Login Member loginMember,
                            @PathVariable Long itemId, @PathVariable Long memberId,
                            @ModelAttribute("order") @Validated OrderForm form, BindingResult result,
                            RedirectAttributes redirectAttributes){

        if (result.hasErrors()){
            return "/items/order/orderForm";
        }
        Long order = orderService.order(memberId, itemId, form.getBuyQuantity());
        redirectAttributes.addAttribute("memberId",memberId);
        redirectAttributes.addAttribute("orderId",order);
        redirectAttributes.addAttribute("status",true);
        log.info("endPostOrder");
        return "redirect:/order/myOrder/{memberId}/{orderId}";
    }

    @GetMapping("/myOrder/{memberId}/{orderId}")
    public String MyOrder(@Login Member loginMember,
                          @PathVariable Long memberId,@PathVariable Long orderId,
                          Model model){
        Member member = memberService.findByIdMember(memberId).get();
        OrderItem orderItem = orderItemService.findOrderItem(orderId);
        model.addAttribute("member",member);
        model.addAttribute("order",orderItem);
        return "/items/order/MyOrder";
    }

    @GetMapping("/MyOrderList/{memberId}")
    public String MyOrderList(@Login Member loginMember,
                              @PathVariable Long memberId, Model model){
        List<MemberOrderDto> myOrderItems = orderService.findMyOrderItems(memberId);
        model.addAttribute("orderItems",myOrderItems);
        model.addAttribute("member",loginMember);
        return "/items/order/MyOrderList";
    }
}

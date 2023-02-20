package study.project.web.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.project.domain.exception.NotEnoughStockException;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;

import study.project.domain.order.OrderItem;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.service.OrderItemService;
import study.project.domain.order.service.OrderService;
import study.project.web.argumentResolver.Login;
import study.project.web.order.form.OrderForm;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    @GetMapping("/{itemId}")
    public String order(@Login Member loginMember,
                        @PathVariable Long itemId, Model model){
        Item item = itemService.findByIdItem(itemId).get();
        OrderForm orderForm = new OrderForm(item.getMember().getLoginId(),itemId,
                item.getItemName(),item.getPrice(),0);

        model.addAttribute("member",loginMember);
        model.addAttribute("order",orderForm);
        log.info("getOrderForm");
        return "/items/order/orderForm" ;
    }
    @PostMapping("/{itemId}")
    public String orderItem(@Login Member loginMember,
                            @PathVariable Long itemId,
                            @ModelAttribute("order") @Valid OrderForm form, BindingResult result,
                            RedirectAttributes redirectAttributes, Model model){

        if (result.hasErrors()){
            model.addAttribute("member",loginMember);
            return "/items/order/orderForm";
        }
        try {
            Long orderId = orderService.order(loginMember.getId(), itemId, form.getBuyQuantity());
            redirectAttributes.addAttribute("orderId",orderId);
            redirectAttributes.addAttribute("status",true);
            log.info("endPostOrder");
            return "redirect:/order/myOrder/{orderId}";
        } catch (NotEnoughStockException e){
            model.addAttribute("member",loginMember);
            redirectAttributes.addAttribute("over",true);
            return "redirect:/order/{itemId}";
        }
    }

    @GetMapping("/myOrder/{orderId}")
    public String MyOrder(@Login Member loginMember, @PathVariable Long orderId,
                          Model model, RedirectAttributes redirectAttributes){
        List<Long> OrderByMemberId = orderService.findByMemberId(loginMember.getId());
        if (!OrderByMemberId.contains(orderId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/order/MyOrderList";
        }
        OrderItem orderItem = orderItemService.findOrderItem(orderId);
        model.addAttribute("member",loginMember);
        model.addAttribute("order",orderItem);
        return "/items/order/MyOrder";
    }

    @GetMapping("/MyOrderList")
    public String MyOrderList(@Login Member loginMember, Model model){
        List<MemberOrderDto> myOrderItems = orderService.findMyOrderItems(loginMember.getId());
        model.addAttribute("orderItems",myOrderItems);
        model.addAttribute("member",loginMember);
        return "/items/order/MyOrderList";
    }
    @GetMapping("/cancel/{orderId}")
    public String cancel(@Login Member loginMember, @PathVariable Long orderId,
                         RedirectAttributes redirectAttributes){
        System.out.println("orderId = " + orderId);
        List<Long> OrderByMemberId = orderService.findByMemberId(loginMember.getId());

        if (!OrderByMemberId.contains(orderId)){
            redirectAttributes.addAttribute("impossible",true);
            return "redirect:/order/myOrder/{orderId}";
        }
        orderService.cancelOrder(orderId);
        redirectAttributes.addAttribute("possible",true);
        return "redirect:/order/MyOrderList";
    }

}

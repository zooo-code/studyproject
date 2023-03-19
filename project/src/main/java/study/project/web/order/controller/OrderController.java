package study.project.web.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.project.domain.exception.NotEnoughStockException;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;

import study.project.domain.order.OrderItem;
import study.project.domain.order.dto.CustomerOrderList;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.service.OrderItemService;
import study.project.domain.order.service.OrderService;
import study.project.page.Pagination;
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
                            @ModelAttribute("order") @Validated OrderForm form, BindingResult result,
                            RedirectAttributes redirectAttributes, Model model){

        if (result.hasErrors()){
            model.addAttribute("member",loginMember);
            model.addAttribute("order",form);
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
    public String MyOrderList(@Login Member loginMember, Model model,
                              @RequestParam(defaultValue = "1") int page){
        int allCnt = orderService.findMyOrderItems(loginMember.getId()).size();
        //페이지
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
        model.addAttribute("page",page);
        model.addAttribute("pagination",pagination);

        List<MemberOrderDto> myOrderItems = orderService.myOrderListPaging(loginMember.getId(),startIndex,pageSize );
        model.addAttribute("orderItems",myOrderItems);
        model.addAttribute("member",loginMember);

        return "/items/order/MyOrderList";
    }
    @GetMapping("/cancel/{orderId}")
    public String cancel(@Login Member loginMember, @PathVariable Long orderId,
                         RedirectAttributes redirectAttributes){

        List<Long> OrderByMemberId = orderService.findByMemberId(loginMember.getId());

        if (!OrderByMemberId.contains(orderId)){
            redirectAttributes.addAttribute("impossible",true);
            return "redirect:/order/myOrder/{orderId}";
        }
        orderService.cancelOrder(orderId);
        redirectAttributes.addAttribute("possible",true);
        return "redirect:/order/MyOrderList";
    }

    @PostMapping("/delete/{orderId}")
    public String delete(@Login Member loginMember, @PathVariable Long orderId,RedirectAttributes redirectAttributes){
        String deleteOrder = orderService.deleteOrder(orderId);
        redirectAttributes.addAttribute("delete", deleteOrder);

        return "redirect:/order/MyOrderList";
    }

    @GetMapping("/customerOrderList")
    public String customerOrderList(@Login Member loginMember, Model model,

                                    @RequestParam(defaultValue = "1") int page){
        int allCnt = orderService.customerOrderList(loginMember.getId()).size();
        //페이지
        Pagination pagination = new Pagination(allCnt, page);

        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        model.addAttribute("page",page);
        model.addAttribute("pagination",pagination);

        List<CustomerOrderList> customerOrderLists = orderService.customerOrderListPaging(loginMember.getId(), startIndex, pageSize);
        model.addAttribute("customerOrderList",customerOrderLists);
        model.addAttribute("member",loginMember);

        return "/items/order/customerOrderList";
    }

    @GetMapping("/customerOrder/{orderId}")
    public String customerOrder(@Login Member loginMember,
                                @PathVariable Long orderId, Model model,RedirectAttributes redirectAttributes){
        OrderItem orderItem = orderItemService.findOrderItem(orderId);
        Member customer = orderService.findMember(orderId);
        if (customer == null){
            redirectAttributes.addAttribute("status", true);
            return "redirect:/order/customerOrderList";
        }
        List<Long> customerOrderIds = orderService.customerOrderId(loginMember.getId());
        if (!customerOrderIds.contains(orderId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/order/customerOrderList";
        }
        model.addAttribute("orderItem",orderItem);
        model.addAttribute("member",loginMember);
        model.addAttribute("customer",customer);
        return "/items/order/customerOrder";
    }

    @GetMapping("/cancel/customer/{orderId}")
    public String cancelCustomerOrder(@PathVariable Long orderId, @Login Member loginMember,
                                      RedirectAttributes redirectAttributes){
        List<Long> customerOrderIds = orderService.customerOrderId(loginMember.getId());
        if (!customerOrderIds.contains(orderId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/order/customerOrderList";
        }
        orderService.cancelOrder(orderId);
        redirectAttributes.addAttribute("cancel", true);

        return "redirect:/order/customerOrderList";
    }

    @PostMapping("/delivery/customer/{orderId}")
    public String deliveryCustomerOrder(@PathVariable Long orderId, @Login Member loginMember,
                                        RedirectAttributes redirectAttributes){
        List<Long> customerOrderIds = orderService.customerOrderId(loginMember.getId());

        return "xx";
    }
}

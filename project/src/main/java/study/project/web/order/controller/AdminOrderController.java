package study.project.web.order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.order.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/order")
public class AdminOrderController {


    private final OrderService orderService;


    @GetMapping
    public String allOrder(){

        return "items/order/allOrderList";
    }
}

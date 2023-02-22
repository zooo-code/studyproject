package study.project.api.order.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.project.api.order.dto.OrderDto;
import study.project.api.order.dto.SimpleOrderDto;
import study.project.domain.order.Order;
import study.project.domain.order.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @GetMapping("/api/order")
    public List<SimpleOrderDto> order(){
        List<Order> orders = orderService.finaAll();
//        List<Order> orders = orderService.findAllWithMember();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }
//지연 로딩으로 너무 많은 SQL 실행
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderService.finaAll();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        return result;

    }
}

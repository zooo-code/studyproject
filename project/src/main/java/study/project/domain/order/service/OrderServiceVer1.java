package study.project.domain.order.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;
import study.project.domain.order.repository.OrderRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderServiceVer1 implements OrderService{

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Item> item = itemRepository.findById(itemId);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member.get(), orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    @Override
    @Transactional
    public void cancelOrder(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().cancel();
    }
}

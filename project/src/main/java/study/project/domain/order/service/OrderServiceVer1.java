package study.project.domain.order.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;
import study.project.domain.order.dto.MemberOrderDto;
import study.project.domain.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return order;
    }

    @Override
    public List<MemberOrderDto> findMyOrderItems(Long memberId) {
        return orderRepository.myOrderList(memberId);
    }

    @Override
    public ArrayList<Long> findByMemberId(Long memberId) {
        List<Order> byMemberId = orderRepository.findByMemberId(memberId);
        ArrayList<Long> Orders = new ArrayList<>();
        for (Order order : byMemberId) {
            Orders.add(order.getId());
        }
        return Orders;
    }

    @Override
    public List<Order> finaAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllWithMember() {
        return orderRepository.findAllWithMember();
    }

    @Override
    public List<Order> findAllWithItem() {
        return orderRepository.findAllWithItem();
    }

    @Override
    public List<Order> findAllWithMember(int offset, int limit) {
        return orderRepository.findAllWithMember(offset,limit);
    }

    @Override
    public List<MemberOrderDto> myOrderListPaging(Long memberId, int startIndex, int pageSize) {
        return orderRepository.myOrderListPaging(memberId,startIndex,pageSize);
    }


}

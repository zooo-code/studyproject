package study.project.domain.order.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.delivery.Delivery;
import study.project.domain.delivery.DeliveryStatus;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;
import study.project.domain.order.Order;
import study.project.domain.order.OrderItem;
import study.project.domain.order.OrderStatus;
import study.project.domain.order.dto.CustomerOrderList;
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
        Delivery delivery = new Delivery();
        delivery.setAddress(member.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문 생성
        Order order = Order.createOrder(member.get(),delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public Order startDelivery(Long memberId, Long orderId) {

        List<CustomerOrderList> customerOrderLists = orderRepository.customerOrderList(memberId);
        List<Long> customerOrderId = new ArrayList<>();
        for (CustomerOrderList customerOrderList : customerOrderLists) {
            customerOrderId.add(customerOrderList.getOrderId());
        }
        boolean contains = customerOrderId.contains(orderId);
        if (!contains){
            return null;
        }
        Optional<Order> order = orderRepository.findById(orderId);
        Delivery delivery = order.get().getDelivery();
        delivery.setStatus(DeliveryStatus.COMP);
        return order.get();
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

    @Transactional
    @Override
    public String deleteOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            if (order.get().getStatus() == OrderStatus.CANCEL){
                orderRepository.delete(order.get());
                return "삭제 완료";
            }else {
                return "주문을 취소후 진행해 주세요";
            }
        }else {
            return "존재하지 않는 주문입니다.";
        }
    }

    @Override
    public List<CustomerOrderList> customerOrderList(Long memberId) {
        return orderRepository.customerOrderList(memberId);
    }

    @Override
    public List<CustomerOrderList> customerOrderListPaging(Long memberId, int startIndex, int pageSize) {
        return orderRepository.customerOrderListPaging(memberId, startIndex, pageSize);
    }

    @Override
    public Member findMember(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(Order::getMember).orElse(null);
    }

    @Override
    public List<Long> customerOrderId(Long memberId) {

        List<CustomerOrderList> customerOrderLists = orderRepository.customerOrderList(memberId);

        List<Long> customerOrderIds = new ArrayList<>();
        for (CustomerOrderList customerOrderList : customerOrderLists) {
            customerOrderIds.add(customerOrderList.getOrderId());
        }
        return customerOrderIds;
    }


}

package study.project.domain.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.project.domain.member.Member;
import study.project.domain.order.Order;
import study.project.domain.order.dto.CustomerOrderList;
import study.project.domain.order.dto.MemberOrderDto;
import java.util.List;


public interface OrderService {
    Long order(Long memberId, Long itemId, int count);

    Order startDelivery(Long memberId, Long orderId);

    void cancelOrder(Long orderId);

    Order findById(Long orderId);

    List<MemberOrderDto> findMyOrderItems(Long memberId);

    List<Long> findByMemberId(Long memberId);

    List<Order> finaAll();


    List<Order> findAllWithItem();

    List<Order> findAllWithMember(int offset, int limit);

    List<MemberOrderDto> myOrderListPaging(Long memberId,int startIndex, int pageSize);

    String deleteOrder(Long orderId);

    List<CustomerOrderList> customerOrderList(Long memberId);

    List<CustomerOrderList> customerOrderListPaging(Long memberId,int startIndex, int pageSize);

    Member findMember(Long orderId);

    List<Long> customerOrderId(Long memberId);
}

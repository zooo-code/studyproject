package study.project.domain.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.project.domain.order.Order;
import study.project.domain.order.dto.CustomerOrderList;
import study.project.domain.order.dto.MemberOrderDto;

import java.util.List;

public interface OrderRepositoryCustom {

    List<MemberOrderDto> myOrderList(Long memberId);

    List<Order> findAllWithMember();

    List<Order> findAllWithItem();

    List<Order> findAllWithMember(int offset, int limit);

    List<MemberOrderDto> myOrderListPaging(Long memberId,int startIndex, int pageSize);

    List<CustomerOrderList> customerOrderList(Long memberId);

    List<CustomerOrderList> customerOrderListPaging(Long memberId, int startIndex, int pageSize);


}

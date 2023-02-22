package study.project.domain.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.project.domain.order.Order;
import study.project.domain.order.dto.MemberOrderDto;

import java.util.List;

public interface OrderRepositoryCustom {

    List<MemberOrderDto> myOrderList(Long memberId);

    List<Order> findAllWithMember();

    List<Order> findAllWithItem();


}

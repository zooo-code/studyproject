package study.project.domain.order.repository;

import study.project.domain.order.dto.MemberOrderDto;

import java.util.List;

public interface OrderRepositoryCustom {

    List<MemberOrderDto> myOrderList(Long memberId);
}

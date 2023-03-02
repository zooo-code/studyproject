package study.project.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.order.OrderItem;
import study.project.domain.order.repository.OrderItemRepository;
import study.project.domain.order.repository.OrderRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderItemServiceVer1 implements OrderItemService{
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderItem findOrderItem(Long OrderId) {
        return orderItemRepository.findByOrderId(OrderId);
    }

    @Override
    public boolean findItems(Long itemId) {
        int size = orderItemRepository.findByItemId(itemId).size();
        return size != 0;
    }
}

package study.project.domain.item.service;

import study.project.domain.item.Item;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item saveItem(Item item);
    void deleteItem(Item item);
    Optional<Item> findByIdItem(Long itemId);

    void updateItem(Long Id);

    List<Item> findAllItems();

    List<MemberItemDto> myItemList(Long memberId);

    void edit(Long id, String name, int price, int stockQuantity);
}

package study.toy.domain.item.repository;

import study.toy.domain.item.Item;
import study.toy.domain.item.service.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);
    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

    void update(Long itemId, ItemUpdateDto updateParam);
}

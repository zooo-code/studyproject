package study.toy.domain.item.service;

import study.toy.domain.item.Item;
import study.toy.domain.item.repository.ItemSearchCond;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond cond);
}

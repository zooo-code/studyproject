package study.project.domain.item.service;

import study.project.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item saveItem(Item item);
    void deleteItem(Item item);
    Optional<Item> findByIdItem(Long itemId);

    void updateItem(Long Id);

    List<Item> findAllItems();
}

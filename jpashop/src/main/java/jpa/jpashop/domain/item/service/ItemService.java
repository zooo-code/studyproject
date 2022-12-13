package jpa.jpashop.domain.item.service;

import jpa.jpashop.domain.item.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> findItems();

    Item findOne(Long itemId);

    void updateItem(Long itemId, String name, int price, int stockQuantity);
}

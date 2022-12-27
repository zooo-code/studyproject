package jpa.jpashop.domain.item.service;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.web.item.form.ItemForm;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> findItems();

    Item findOne(Long itemId);

    void updateItem(Long itemId, ItemForm itemForm);

//    Item deleteItem(Long id);
}

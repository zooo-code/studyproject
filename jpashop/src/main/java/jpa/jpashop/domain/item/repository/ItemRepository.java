package jpa.jpashop.domain.item.repository;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.web.item.form.ItemForm;

import java.util.List;


public interface ItemRepository {

    void save(Item item);

    Item findOne(Long id);

    List<Item> findAll();
    void update(Long id, ItemForm itemForm);

//    Item deleteItem(Long id);
}

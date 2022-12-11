package jpa.jpashop.domain.item.repository;

import jpa.jpashop.domain.item.Item;

import java.util.List;


public interface ItemRepository {

    void save(Item item);

    Item findOne(Long id);

    List<Item> findAll();

}

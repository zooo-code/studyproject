package jpa.jpashop.domain.item.service;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.repository.ItemRepository;
import jpa.jpashop.web.item.form.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceJPA implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


    @Override
    @Transactional
    public void updateItem(Long itemId, ItemForm itemForm) {
        itemRepository.update(itemId, itemForm);
    }

//    @Override
//    @Transactional
//    public Item deleteItem(Long id) {
//        return itemRepository.deleteItem(id);
//    }

}

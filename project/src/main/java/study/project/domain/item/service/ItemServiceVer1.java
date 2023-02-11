package study.project.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceVer1 implements ItemService{

    private final ItemRepository itemRepository;
    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }
    @Override
    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

    @Override
    public Optional<Item> findByIdItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public void updateItem(Long id) {

    }
}

package study.toy.domain.item.service;

import lombok.RequiredArgsConstructor;
import study.toy.domain.item.Item;
import study.toy.domain.item.repository.ItemRepository;
import study.toy.domain.item.repository.ItemSearchCond;

import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemRepository.update(itemId,updateParam);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }
}

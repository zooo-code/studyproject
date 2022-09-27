package toy.toyproject.domain.item;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setWhere(updateParam.getWhere());
        findItem.setExplain(updateParam.getExplain());
    }

    public void clearStore() {
        store.clear();
    }
}

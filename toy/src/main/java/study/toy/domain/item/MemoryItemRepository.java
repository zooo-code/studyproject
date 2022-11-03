package study.toy.domain.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class MemoryItemRepository implements ItemRepository{


    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public void save(Item item){
        item.setId(sequence++);
        store.put(item.getId(), item);
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public void clearStore(){
        store.clear();
    }
}

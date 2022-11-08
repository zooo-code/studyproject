package study.toy.domain.item;

import java.util.List;

public interface ItemRepository {

    public void save(Item item);
    public Item findById(Long id);

    public List<Item> findAll();
}

package study.toy.domain.item;

public interface ItemRepository {

    public void save(Item item);
    public Item findById(Long id);
}

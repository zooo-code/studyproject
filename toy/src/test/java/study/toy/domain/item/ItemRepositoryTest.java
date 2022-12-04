package study.toy.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.toy.domain.item.repository.ItemRepository;
import study.toy.domain.item.service.ItemUpdateDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class ItemRepositoryTest {


    @Autowired
    ItemRepository itemRepository;

    @Test
    void save(){
        //given
        Item item = new Item("test1", 10000, 1);

        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);
    }
    @Test
    void delete(){
        //given
        Item item = new Item("test1", 10000, 1);

        //when
        itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId()).get();
        //then
        itemRepository.deleteItem(findItem.getId());
        assertThat(itemRepository.findById(item.getId())).isEmpty();
    }
    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        ItemUpdateDto updateParam = new ItemUpdateDto("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}
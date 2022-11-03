package study.toy.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void clearStore(){
        itemRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Item item = new Item("test1", 10000, 1);


        //when
        itemRepository.save(item);
        //then
        Item byId = itemRepository.findById(item.getId());

        Assertions.assertThat(item).isEqualTo(byId);
    }
}
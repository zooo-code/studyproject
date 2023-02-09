package study.project.domain.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.member.Member;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceVer1Test {
    @Autowired
    ItemService itemService;

    @Test
    public void CRUDItem() {
        Member member = new Member("kim", "test", "123");
        //given
        Item test = new Item(member,"test", 10,1000);
        LocalDateTime createItemTime = test.getCreateItemTime();

        //when
        Item item = itemService.saveItem(test);
        Item findItem = itemService.findByIdItem(item.getId()).get();
        //then
        assertThat(findItem).isEqualTo(item);
        assertThat(findItem.getMember()).isEqualTo(test.getMember());
        System.out.println("findItem.getMember() = " + findItem.getMember());

        itemService.deleteItem(findItem);
        Optional<Item> byIdItem = itemService.findByIdItem(item.getId());
        assertThat(byIdItem).isEmpty();
    }

}
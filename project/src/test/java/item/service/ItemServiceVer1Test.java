package item.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.search.ItemSearch;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.item.dto.MemberItemDto;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceVer1Test {

    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    @Test
    public void CRUDItem() {
        Member member = new Member("kim", "test123", "123");
        Member saveMember = memberService.join(member);
        //given
        Item testItem = new Item(saveMember, "testItem", 3, 1000);
        //when
        Item item = itemService.saveItem(testItem);
        Optional<Item> byIdItem = itemService.findByIdItem(item.getId());
        assertThat(item).isEqualTo(byIdItem.get());

        String successDelete = itemService.deleteItem(byIdItem.get().getId());
        assertThat(successDelete).isEqualTo("삭제 완료");
    }
    @Test
    public void MemberItem() {
        Member member = new Member("kim", "test", "123");
        Member saveMember = memberService.join(member);
        //given
        for (int i= 1 ;  i<=10 ;  i++ ){
            Item item = itemService.saveItem(new Item(saveMember,"test"+i, 10,1000));
            member.addItem(item);
        }
        //when
        Member byId = memberService.findByIdMember(saveMember.getId()).get();
        List<Item> items = byId.getItems();
        System.out.println("items = " + items);
        //then
        assertThat(items.size()).isEqualTo(10);
    }

    @Test
    public void allItem() {
        //given
        Long allCnt = itemService.count();
        //when
        List<Item> itemPaging = itemService.findItemPaging(0, 10);
//        Collections.reverse(itemPaging);
        for (Item item : itemPaging) {
            System.out.println("item = " + item.getItemName() +"create time" + item.getCreateItemTime());
        }
    }


}
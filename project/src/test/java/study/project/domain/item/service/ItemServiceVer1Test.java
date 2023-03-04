package study.project.domain.item.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.search.ItemSearch;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.item.dto.MemberItemDto;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemServiceVer1Test {

    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    @Test
    public void CRUDItem() {
        Member member = new Member("kim", "test123123", "123");
        Member saveMember = memberService.join(member);
        //given
        for (int i= 1 ;  i<=5 ;  i++ ){
            Item item = itemService.saveItem(new Item(saveMember,"test"+i, 10,1000));
            member.addItem(item);

        }

        //when
        List<Item> items = saveMember.getItems();
        assertThat(items.size()).isEqualTo(0);
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
    public void itemMember() {
        Member member = new Member("kim", "test1", "123");
        Member saveMember = memberService.join(member);
        for (int i= 1 ;  i<=10 ;  i++ ){
            Item item = itemService.saveItem(new Item(saveMember,"test"+i, 10,1000));
        }
        List<MemberItemDto> memberItem = itemService.myItemList(saveMember.getId());
        //given

        System.out.println(memberItem.size());

        //when

        //then
    }
    @Test
    public void allItem() {
        //given
        int allCnt = itemService.findAllCnt();
        //when
        List<Item> itemPaging = itemService.findItemPaging(0, 10);
//        Collections.reverse(itemPaging);
        for (Item item : itemPaging) {
            System.out.println("item = " + item.getItemName() +"create time" + item.getCreateItemTime());
        }
    }

    @Test
    public void myItemList () {
        //given
        List<MemberItemDto> memberItemDtos = itemService.myItemListPaging(1L, 0, 10);

        //when

        //then
        for (MemberItemDto memberItemDto : memberItemDtos) {
            System.out.println("memberItemDto = " + memberItemDto.getCreateItemTime());
        }
    }
    @Test
    public void itemSearch () {

        //then
    }


}
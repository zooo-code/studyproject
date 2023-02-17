package study.project.domain.item.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;
import study.project.domain.member.service.MemberService;
import study.project.web.item.dto.MemberItemDto;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        Member member = new Member("kim", "test", "123");
        Member saveMember = memberService.join(member);
        //given
        for (int i= 1 ;  i<=10 ;  i++ ){
            Item item = itemService.saveItem(new Item(saveMember,"test"+i, 10,1000));
            member.addItem(item);
        }
        //when
        Optional<Member> byId = memberService.findByIdMember(saveMember.getId());
        List<Item> items = byId.get().getItems();
        System.out.println("items = " + items);
        //then
        assertThat(items.size()).isEqualTo(10);
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
        Optional<Member> byId = memberService.findByIdMember(saveMember.getId());
        List<Item> items = byId.get().getItems();
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

}
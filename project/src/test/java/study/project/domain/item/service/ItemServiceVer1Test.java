package study.project.domain.item.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;

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
    MemberRepository memberRepository;

    @Test
    public void CRUDItem() {
        Member member = new Member("kim", "test", "123");
        Member saveMember = memberRepository.save(member);
        //given
        for (int i= 1 ;  i<=10 ;  i++ ){
            Item item = itemService.saveItem(new Item(saveMember,"test"+i, 10,1000));
            member.addItem(item);
        }
        //when
        Optional<Member> byId = memberRepository.findById(saveMember.getId());
        List<Item> items = byId.get().getItems();
        //then
        assertThat(items.size()).isEqualTo(10);
    }

}
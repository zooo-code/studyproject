package study.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final ItemService itemService;
        public void dbInit1(){
            Member member = new Member("kim", "test", "123");
            Member member1 = memberService.join(member);

            for (int i = 1; i <=10 ; i++){
                Item item = new Item(member1, "kimItem" + i, 10 + i, 1000 * i);
                itemService.saveItem(item);
            }
            Member member2 = new Member("lee", "test12", "123");

            Member member3 = memberService.join(member2);
            for (int i =1; i<=10; i++){
                Item item1 = new Item(member3, "leeItem" + i, i + 1, 100 * i);
                itemService.saveItem(item1);
            }

        }

    }
}

package study.toy.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.toy.domain.member.repository.MemoryMemberRepository;


class MemberRepositoryTest {


    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void clearStore(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member = new Member();
        member.setName("kim");
        member.setLoginId("test1");
        member.setPassword("1234");
        memberRepository.save(member);

        Member byUserId = memberRepository.findById(member.getId());
        System.out.println("byUserId = " + byUserId);

        Assertions.assertThat(member).isEqualTo(byUserId);

    }

}
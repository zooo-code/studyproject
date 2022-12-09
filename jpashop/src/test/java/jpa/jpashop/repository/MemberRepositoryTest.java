package jpa.jpashop.repository;

import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
//    @Rollback(value = false);
    public void testMember(){
        //given
        Member member = new Member();
        member.setUsername("memberA");
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}
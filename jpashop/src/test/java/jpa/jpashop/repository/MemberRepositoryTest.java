package jpa.jpashop.repository;

import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
        member.setName("memberA");
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
//    @Rollback(value = false);
    public void findLoginID(){
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setLoginId("test");
        member.setPassword("123");
        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findByLoginIdForLogin("test");
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        log.info("{}, {}",findMember.getLoginId(),findMember.getPassword());
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}
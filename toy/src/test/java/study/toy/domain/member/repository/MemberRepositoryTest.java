package study.toy.domain.member.repository;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.toy.domain.member.Member;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save(){
        //given
        Member member = new Member("test","test","test");
        //when
        memberRepository.save(member);
        //then
        Optional<Member> findByLoginId = memberRepository.findByLoginId(member.getLoginId());

        Assertions.assertThat(findByLoginId).isEqualTo(member);
    }


}
package study.project.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.address.Address;
import study.project.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Transactional
@SpringBootTest
class memberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void memberCreate() {
        Address address = new Address("123", "서울시", "서울구", "서울동");
        Member member = new Member("kim", "test", "123", address);
        Member save = memberRepository.save(member);

        Member findMember = memberRepository.findById(save.getId()).get();

        assertThat(save).isEqualTo(findMember);
    }

    @Test
    public void transactionTest () {
        //given

        //when

        //then
    }
}
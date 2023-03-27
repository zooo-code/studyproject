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
    public void member() {
        new Address("123","서울시","서울구","서울동");
        new Member("kim","test","123",);
    }
}
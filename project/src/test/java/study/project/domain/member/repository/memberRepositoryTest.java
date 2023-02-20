package study.project.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.project.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class memberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void member() {
        Member member = new Member("kim", "test", "123");
        Member save = memberRepository.save(member);
        System.out.println("save = " + save);
        Optional<Member> findById = memberRepository.findById(save.getId());
        assertThat(member.getId()).isEqualTo(findById.get().getId());

    }
}
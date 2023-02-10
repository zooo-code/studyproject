package study.project.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberServiceVer1Test {
    @Autowired
    MemberService memberService;
    @Test
    public void CRUDTest() {
        //given
        Member member = new Member("kim", "test", "123");
        //when
        Member saveMember = memberService.join(member);
        Member findMember = memberService.findByIdMember(saveMember.getId()).get();

        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
        //then
        assertThat(findMember.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(findMember).isEqualTo(member);

        memberService.deleteMember(findMember);
        long countMember = memberService.countMember();
        assertThat(countMember).isEqualTo(0);

    }
}
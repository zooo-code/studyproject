package jpa.jpashop.domain.member.service;

import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceJPA implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
        }
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

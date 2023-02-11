package study.project.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceVer1 implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByIdMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return Optional.ofNullable(member);
    }

    @Override
    public Member deleteMember(Member member) {
        memberRepository.delete(member);
        return member;
    }

    @Override
    public Long countMember() {
        return memberRepository.count();
    }


}

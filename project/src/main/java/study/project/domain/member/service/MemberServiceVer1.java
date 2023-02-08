package study.project.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceVer1 implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public Member join(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember;
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
    public long countMember() {
        long count = memberRepository.count();
        return count;
    }


}

package study.toy.domain.member;

import java.util.Optional;

public interface MemberRepository {
    public void save(Member member);
    public Member findById(Long id);

    public Optional<Member> findByLoginId(String loginId);
}

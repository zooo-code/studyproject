package study.toy.domain.member;

import java.util.Optional;

public class H2MemberRepository implements MemberRepository{
    @Override
    public void save(Member member) {

    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return Optional.empty();
    }
}

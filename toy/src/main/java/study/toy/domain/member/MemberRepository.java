package study.toy.domain.member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    void delete(String login_id);

    void update(String login_id, int money);
}

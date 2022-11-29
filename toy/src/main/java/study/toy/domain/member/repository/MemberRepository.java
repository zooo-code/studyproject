package study.toy.domain.member.repository;

import org.springframework.stereotype.Repository;
import study.toy.domain.member.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    void delete(String login_id);

    void update(String login_id, int money);
}

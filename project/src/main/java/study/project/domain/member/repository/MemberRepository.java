package study.project.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.member.Member;
import study.project.log.LogAopTrace;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom{

    @LogAopTrace
    Optional<Member> findByLoginId(String LoginId);
}

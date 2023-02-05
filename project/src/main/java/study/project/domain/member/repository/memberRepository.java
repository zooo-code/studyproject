package study.project.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.member.Member;

public interface memberRepository extends JpaRepository<Member,Long>{
    
}

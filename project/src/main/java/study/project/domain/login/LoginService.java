package study.project.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent() && findMember.get().getPassword().equals(password)){
            return findMember.get();
        }
        return null;
    }
}

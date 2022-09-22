package toy.toyproject.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.toyproject.domain.member.Member;
import toy.toyproject.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String email, String password){
        return memberRepository.findByEmail(email).filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}

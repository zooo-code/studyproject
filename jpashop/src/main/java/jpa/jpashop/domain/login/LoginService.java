package jpa.jpashop.domain.login;

import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Member byLoginIdForLogin = memberRepository.findByLoginIdForLogin(loginId);
        if (byLoginIdForLogin.getPassword().equals(password)){
            log.info("{},{}",byLoginIdForLogin.getLoginId(),byLoginIdForLogin.getPassword());
            return byLoginIdForLogin;
        }
        return null;
    }
}

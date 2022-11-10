package study.toy.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemoryMemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class loginService {

    private final MemoryMemberRepository memoryMemberRepository;

    public Member login(String loginId, String password) {
        return memoryMemberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}

package study.toy.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemoryMemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryMemberRepository memoryMemberRepository;

    public Member login(String loginId, String password) {
//        Optional<Member> findMemberOptional = memoryMemberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get();
//
//        if (member.getPassword().equals(password)) {
//            return member;
//        }else {
//            return null;
//        }
        return memoryMemberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}

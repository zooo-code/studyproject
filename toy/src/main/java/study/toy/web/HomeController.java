package study.toy.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemoryMemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemoryMemberRepository memoryMemberRepository;
    @GetMapping("/home")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        if (loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}

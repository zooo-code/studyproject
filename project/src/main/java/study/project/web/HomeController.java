package study.project.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.member.Member;
import study.project.web.argumentResolver.Login;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String Home(@Login Member loginMember, Model model){

        if (loginMember == null){
            return "home";
        }
        log.info("home");
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}

package jpa.jpashop.web;


import jpa.jpashop.argumentResolver.Login;
import jpa.jpashop.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(@Login Member loginMember, Model model) {
        log.info("home controller");

        if (loginMember == null) {
            log.info("home not loginMember");
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}
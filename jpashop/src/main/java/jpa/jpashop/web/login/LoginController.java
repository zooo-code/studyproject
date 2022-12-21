package jpa.jpashop.web.login;
import jpa.jpashop.domain.login.LoginService;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.interceptor.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm form) {
        return "login/loginForm" ;
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){

        if (result.hasErrors()){
            log.info("로그인 에러입니다.{},{}",form.getLoginId(),form.getPassword());
           return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        Member login = loginService.login(form.getLoginId(), form.getPassword());
        if (login == null){
            result.reject("loginFail", "아이디 또는 비밀번호를 찾을 수 없습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        log.info("logout");
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}

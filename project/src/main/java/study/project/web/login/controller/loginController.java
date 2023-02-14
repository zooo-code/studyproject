package study.project.web.login.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.project.domain.login.LoginService;
import study.project.domain.member.Member;
import study.project.web.interceptor.SessionConst;
import study.project.web.login.form.loginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class loginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm",new loginForm());
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute loginForm form, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request){
        if (result.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null){
            result.reject("loginFail", "아이디 또는 비밀번호를 찾을 수 없습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        return "redirect:"+ redirectURL;
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}

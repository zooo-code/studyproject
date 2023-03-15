package study.project.web.login.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.login.LoginService;
import study.project.web.login.form.loginForm;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm",new loginForm());
        return "login/adminLoginForm";
    }
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute loginForm form){
        

        return "redirect:";
    }
}

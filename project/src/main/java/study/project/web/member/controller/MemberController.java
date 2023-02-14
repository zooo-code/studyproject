package study.project.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.member.form.MemberForm;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Validated MemberForm form, BindingResult result){
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = new Member(form.getUsername(),form.getLoginId(),form.getPassword());
        memberService.join(member);
        return "redirect:/";
    }
}

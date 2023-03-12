package study.project.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.project.domain.address.Address;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.argumentResolver.Login;
import study.project.web.member.form.MemberForm;
import study.project.web.member.form.UpdateMemberForm;

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
    public String create(@ModelAttribute("memberForm") @Validated MemberForm form, BindingResult result,
                         RedirectAttributes redirectAttributes,Model model){

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Boolean findByLoginId = memberService.findByLoginId(form.getLoginId());
        if (findByLoginId){
            result.reject("sameLoginId","중복된 아이디 입니다.");
            return "members/createMemberForm";
        }
        Address address = new Address(form.getZipCode(), form.getAddress(),form.getDetailAddress(), form.getEtc());
        Member member = new Member(form.getUsername(),form.getLoginId(),form.getPassword(),address);
        memberService.join(member);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/";
    }

    @GetMapping("/myInfo")
    public String myInfo(@Login Member loginMember,
                          Model model){
        model.addAttribute("member",loginMember);
        return "members/memberInfo";
    }
    @GetMapping("/edit")
    public String edit(@Login Member loginMember, Model model){
        UpdateMemberForm updateMemberForm = new UpdateMemberForm(loginMember.getId(), loginMember.getLoginId(),
                loginMember.getUsername(), loginMember.getPassword(),loginMember.getAddress().getZipCode(),loginMember.getAddress().getAddress(),
                loginMember.getAddress().getDetailAddress(),loginMember.getAddress().getEtc());
        model.addAttribute("member", updateMemberForm);
        return "members/editMemberInfo";
    }
    @PostMapping("/edit")
    public String editMember(@Login Member loginMember, @ModelAttribute("member")@Validated UpdateMemberForm form, BindingResult result,
                             RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "/members/editMemberInfo";
        }
        Address address = new Address(form.getZipCode(),form.getAddress(),form.getDetailAddress(),form.getEtc());
        memberService.edit(loginMember.getId(),form.getUsername(),form.getPassword(),address );
        redirectAttributes.addAttribute("memberId",loginMember.getId());
        return "redirect:/members/myInfo";
    }

    @GetMapping("/setting")
    public String setting(@Login Member loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "MySetting";
    }

    @PostMapping("/deleteMember")
    public String deleteMember(@Login Member loginMember, RedirectAttributes redirectAttributes ){
         if (memberService.checkOrderAndItem(loginMember.getId())){
             redirectAttributes.addAttribute("check", true);
             return "redirect:/members/myInfo";
         }

        memberService.deleteMember(loginMember);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/login";
    }
}

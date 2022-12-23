package jpa.jpashop.web.member.controller;


import jpa.jpashop.domain.delivery.Address;
import jpa.jpashop.domain.member.Member;
import jpa.jpashop.domain.member.repository.MemberRepository;
import jpa.jpashop.domain.member.service.MemberService;
import jpa.jpashop.web.member.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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
        if (result.hasErrors()){
            return "members/createMemberForm";
        }
//        이거 set 수정해야함
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }

    //추가
    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

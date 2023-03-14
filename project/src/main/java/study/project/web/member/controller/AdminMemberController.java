package study.project.web.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/members")
public class AdminMemberController {

    private final MemberService memberService;


    @GetMapping
    public String allMember(){
        return "members/admin/allMembers";
    }

    @GetMapping("/member/{memberId}")
    public String memberInfo(@PathVariable Long memberId){

        return "members/memberInfo";
    }
}

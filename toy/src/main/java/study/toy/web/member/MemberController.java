package study.toy.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.toy.domain.member.Member;
import study.toy.domain.member.repository.MemberRepository;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member){
        return "/members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/home";
    }



    @GetMapping("/{loginId}")
    public String member(@PathVariable String loginId, Model model){
        Optional<Member> Member = memberRepository.findByLoginId(loginId);
        model.addAttribute("member",Member);
        return "members/member";
    }
    @GetMapping()
    public String editForm(@PathVariable String loginId, Model model){

        return "members/memberEditForm";
    }


}

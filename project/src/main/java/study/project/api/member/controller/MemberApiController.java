package study.project.api.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.project.api.member.dto.CreateMemberRequest;
import study.project.api.member.dto.UpdateMemberRequest;
import study.project.api.member.response.CreateMemberResponse;
import study.project.api.member.response.UpdateMemberResponse;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/member/join")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member(request.getUsername(), request.getLoginId(), request.getPassword());
        Member join = memberService.join(member);
        return new CreateMemberResponse(join.getId());
    }

    @PutMapping("/api/member/update/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){
        memberService.edit(id, request.getName(), request.getPassword());
        Member member = memberService.findByIdMember(id).get();
        return new UpdateMemberResponse(member.getId(), member.getUsername(), member.getPassword());
    }


}

package study.project.api.member.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.project.api.member.dto.CreateMemberRequest;
import study.project.api.member.dto.MemberDto;
import study.project.api.member.dto.UpdateMemberRequest;
import study.project.api.member.response.AllMembers;
import study.project.api.member.response.CreateMemberResponse;
import study.project.api.member.response.UpdateMemberResponse;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     *여기서 PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용하는 것이 맞다. 부분 업데이트를 하려면 PATCH를
     * 사용하거나 POST를 사용하는 것이 REST 스타일에 맞다.
     */
    @PutMapping("/api/member/update/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){
        memberService.edit(id, request.getUsername(), request.getPassword(),null);
        Member member = memberService.findByIdMember(id).get();
        return new UpdateMemberResponse(member.getId(), member.getUsername(), member.getPassword());
    }

    @GetMapping("/api/members")
    public AllMembers members(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream().map(m -> new MemberDto(m.getUsername(), m.getLoginId()))
                .collect(Collectors.toList());
        return new AllMembers(collect);
    }
    @Data
    @AllArgsConstructor
    static class AllMembers<T> {
        private T data;
    }



}

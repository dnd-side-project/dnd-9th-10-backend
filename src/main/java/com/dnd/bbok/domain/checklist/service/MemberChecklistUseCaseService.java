package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.domain.checklist.dto.request.MemberChecklistRequestDto;
import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.exception.MemberNotFoundException;
import com.dnd.bbok.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.dnd.bbok.global.exception.ErrorCode.MEMBER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class MemberChecklistUseCaseService {
    private final MemberService memberService;
    private final MemberChecklistService memberChecklistService;

    public List<MemberChecklist> createMemberChecklistEntities(MemberChecklistRequestDto checklistRequestDto, UUID uuid) {
        Member member = memberService.getMemberById(uuid)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
        List<MemberChecklist> checklist = new ArrayList<>();

        checklistRequestDto.getBadChecklist().forEach(ele -> {
            checklist.add(MemberChecklist.builder()
                    .member(member)
                    .criteria(ele)
                    .isGood(false)
                    .isUsed(true)
                    .build());
        });

        checklistRequestDto.getGoodChecklist().forEach(ele -> {
            checklist.add(MemberChecklist.builder()
                    .member(member)
                    .criteria(ele)
                    .isGood(true)
                    .isUsed(true)
                    .build());
        });


        return memberChecklistService.createMemberChecklist(checklist);
    }
}

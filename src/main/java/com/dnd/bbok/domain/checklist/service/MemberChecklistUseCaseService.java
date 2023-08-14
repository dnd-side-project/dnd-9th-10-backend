package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.domain.checklist.dto.request.MemberChecklistRequestDto;
import com.dnd.bbok.domain.checklist.dto.response.ChecklistInfoDto;
import com.dnd.bbok.domain.checklist.dto.response.MyChecklistDto;
import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.service.MemberService;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberChecklistUseCaseService {
    private final MemberService memberService;
    private final MemberChecklistEntityService memberChecklistEntityService;
    final boolean GOOD = true;
    final boolean BAD = false;

    public void createMemberChecklistEntities(MemberChecklistRequestDto checklistRequestDto, UUID uuid) {
        Member member = memberService.getMemberById(uuid);
        List<MemberChecklist> checklist = new ArrayList<>();

        checklistRequestDto.getBadChecklist().forEach(ele -> checklist.add(MemberChecklist.builder()
                .member(member)
                .criteria(ele)
                .isGood(false)
                .isUsed(true)
                .build()));

        checklistRequestDto.getGoodChecklist().forEach(ele -> checklist.add(MemberChecklist.builder()
                .member(member)
                .criteria(ele)
                .isGood(true)
                .isUsed(true)
                .build()));


        memberChecklistEntityService.createMemberChecklist(checklist);
    }

    public MyChecklistDto getMemberChecklist(UUID memberId) {
        List<MemberChecklist> checklistInUsing =
            memberChecklistEntityService.getMemberChecklistInUsing(memberId);

        Map<Boolean, List<MemberChecklist>> checklistMap = checklistInUsing.stream()
            .collect(Collectors.partitioningBy(MemberChecklist::isGood));

        List<ChecklistInfoDto> goodList = changeToListInfoDto(checklistMap, GOOD);
        List<ChecklistInfoDto> badList = changeToListInfoDto(checklistMap, BAD);
        return new MyChecklistDto(goodList, badList);
    }

    private List<ChecklistInfoDto> changeToListInfoDto(
        Map<Boolean, List<MemberChecklist>> checklistMap, boolean isGood) {
        return checklistMap.getOrDefault(isGood, Collections.emptyList())
            .stream()
            .map(ChecklistInfoDto::new)
            .collect(Collectors.toList());
    }
}

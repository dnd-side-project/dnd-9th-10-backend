package com.dnd.bbok.member.adapter.out.persistence.mapper;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.domain.ChecklistInfo;
import com.dnd.bbok.member.domain.MemberChecklist;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MemberChecklistMapper {
    public MemberChecklist toDomain(List<MemberChecklistEntity> memberChecklistEntities) {
        log.info(String.valueOf(memberChecklistEntities.size()));
        memberChecklistEntities.forEach(ele -> log.info(ele.getCriteria()));
        List<ChecklistInfo> goodChecklist = new ArrayList<>();
        List<ChecklistInfo> badChecklist = new ArrayList<>();
        memberChecklistEntities.stream().filter(MemberChecklistEntity::isGood).forEach(ele ->
            goodChecklist.add(new ChecklistInfo(ele.getId(), ele.getCriteria(), true, ele.isUsed()))
        );

        memberChecklistEntities.stream().filter(ele -> !ele.isGood()).forEach(ele ->
            badChecklist.add(new ChecklistInfo(ele.getId(), ele.getCriteria(), false, ele.isUsed()))
        );

        return new MemberChecklist(goodChecklist, badChecklist);
    }

    public List<MemberChecklistEntity> toEntity(MemberChecklist memberChecklist, MemberEntity member) {
        List<MemberChecklistEntity> memberChecklistEntities = new ArrayList<>();
        memberChecklist.getGoodChecklist().forEach(ele -> memberChecklistEntities.add(MemberChecklistEntity.builder()
                .id(ele.getId())
                .isGood(true)
                .isUsed(ele.isUsed())
                .criteria(ele.getCriteria())
                .member(member)
                .build()));

        memberChecklist.getBadChecklist().forEach(ele -> memberChecklistEntities.add(MemberChecklistEntity.builder()
                .id(ele.getId())
                .isGood(false)
                .isUsed(ele.isUsed())
                .criteria(ele.getCriteria())
                .member(member)
                .build()));

        return memberChecklistEntities;
    }

    public ChecklistInfo toOneDomain(MemberChecklistEntity memberChecklistEntity) {
        return new ChecklistInfo(
            memberChecklistEntity.getId(),
            memberChecklistEntity.getCriteria(),
            memberChecklistEntity.isGood(),
            memberChecklistEntity.isUsed());
    }

    public List<MemberChecklistEntity> updateEntity(MemberEntity member, List<ChecklistInfo> newChecklist) {
        return newChecklist.stream()
            .map(checklist -> MemberChecklistEntity.builder()
                .id(checklist.getId())
                .member(member)
                .isUsed(checklist.isUsed())
                .isGood(checklist.isGood())
                .criteria(checklist.getCriteria())
                .build())
            .collect(Collectors.toList());
    }
}

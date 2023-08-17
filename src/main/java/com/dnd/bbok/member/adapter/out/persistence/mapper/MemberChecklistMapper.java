package com.dnd.bbok.member.adapter.out.persistence.mapper;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.domain.ChecklistInfo;
import com.dnd.bbok.member.domain.MemberChecklist;
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
            goodChecklist.add(new ChecklistInfo(ele.getId(), ele.getCriteria()))
        );

        memberChecklistEntities.stream().filter(ele -> !ele.isGood()).forEach(ele ->
            badChecklist.add(new ChecklistInfo(ele.getId(), ele.getCriteria()))
        );

        return new MemberChecklist(goodChecklist, badChecklist);
    }

    public List<MemberChecklistEntity> toEntity(MemberChecklist memberChecklist, MemberEntity member) {
        List<MemberChecklistEntity> memberChecklistEntities = new ArrayList<>();
        memberChecklist.getGoodChecklist().forEach(ele -> memberChecklistEntities.add(MemberChecklistEntity.builder()
                .id(ele.getId())
                .isGood(true)
                .isUsed(true)
                .criteria(ele.getCriteria())
                .member(member)
                .build()));

        memberChecklist.getBadChecklist().forEach(ele -> memberChecklistEntities.add(MemberChecklistEntity.builder()
                .id(ele.getId())
                .isGood(false)
                .isUsed(true)
                .criteria(ele.getCriteria())
                .member(member)
                .build()));

        return memberChecklistEntities;
    }
}

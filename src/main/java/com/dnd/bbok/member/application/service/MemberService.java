package com.dnd.bbok.member.application.service;
import com.dnd.bbok.member.application.port.in.request.AddedChecklistInfo;
import com.dnd.bbok.member.application.port.in.request.ModifiedChecklistInfoRequest;
import com.dnd.bbok.member.application.port.in.request.CreateMemberChecklistRequest;
import com.dnd.bbok.member.application.port.in.request.EditMemberChecklistRequest;
import com.dnd.bbok.member.application.port.in.response.GetDetailMemberChecklistResponse;
import com.dnd.bbok.member.application.port.in.response.GetMemberChecklistResponse;
import com.dnd.bbok.member.application.port.in.response.GetMemberInfoResponse;

import com.dnd.bbok.member.application.port.in.usecase.*;

import com.dnd.bbok.member.application.port.out.DeleteMemberPort;
import com.dnd.bbok.member.application.port.out.LoadMemberChecklistPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.application.port.out.SaveMemberChecklistPort;

import com.dnd.bbok.member.domain.ChecklistInfo;
import com.dnd.bbok.member.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dnd.bbok.member.domain.MemberChecklist;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Usecase의 구현체. 출력 port들을 가지고 있다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements
    GetMemberQuery, CreateMemberChecklistUseCase, GetMemberChecklistQuery,
    EditMemberChecklistUseCase, DeleteMemberUseCase {

  private final LoadMemberPort loadMemberPort;
  private final LoadMemberChecklistPort loadMemberChecklistPort;
  private final SaveMemberChecklistPort saveMemberChecklistPort;
  private final DeleteMemberPort deleteMemberPort;

  @Override
  public GetMemberInfoResponse getMember(UUID memberId) {
    Member member = loadMemberPort.loadById(memberId);
    return new GetMemberInfoResponse(member);
  }

  @Override
  public void createMemberChecklist(UUID memberId, CreateMemberChecklistRequest createMemberChecklistRequest) {
    List<ChecklistInfo> goodChecklist = new ArrayList<>();
    List<ChecklistInfo> badChecklist = new ArrayList<>();

    createMemberChecklistRequest.getBadChecklist().forEach(ele ->
      badChecklist.add(new ChecklistInfo(null, ele.getCriteria(), false, ele.getIsUsed()))
    );

    createMemberChecklistRequest.getGoodChecklist().forEach(ele ->
      goodChecklist.add(new ChecklistInfo(null, ele.getCriteria(), true, ele.getIsUsed()))
    );


    MemberChecklist memberChecklist = new MemberChecklist(goodChecklist, badChecklist);
    saveMemberChecklistPort.saveMemberChecklist(memberId, memberChecklist);
  }

  @Override
  public GetMemberChecklistResponse getMemberChecklistQuery(UUID memberId) {
    MemberChecklist memberChecklist = loadMemberChecklistPort.loadMemberChecklist(memberId);
    // log.info(memberChecklist.getGoodChecklist().get(0).getCriteria());
    // log.info(memberChecklist.getBadChecklist().get(0).getCriteria());

    List<GetDetailMemberChecklistResponse> badChecklist = memberChecklist.getBadChecklist()
        .stream()
        .map(ele -> new GetDetailMemberChecklistResponse(ele.getId(), ele.getCriteria(), ele.isUsed()))
        .collect(Collectors.toList());

    List<GetDetailMemberChecklistResponse> goodChecklist = memberChecklist.getGoodChecklist()
        .stream()
        .map(ele -> new GetDetailMemberChecklistResponse(ele.getId(), ele.getCriteria(), ele.isUsed()))
        .collect(Collectors.toList());
    return new GetMemberChecklistResponse(goodChecklist, badChecklist) ;
  }

  /**
   * member checklist 수정하기
   */
  @Override
  public void edit(UUID memberId, EditMemberChecklistRequest memberChecklistRequest) {
    List<ChecklistInfo> updateChecklist = new ArrayList<>();

    //수정하려는 체크리스트를 가져온다.
    List<ModifiedChecklistInfoRequest> requestChecklist = memberChecklistRequest.getModifiedBadChecklist();
    requestChecklist.addAll(memberChecklistRequest.getModifiedGoodChecklist());

    for(ModifiedChecklistInfoRequest checklist : requestChecklist) {
      ChecklistInfo checklistInfo = loadMemberChecklistPort.loadOneById(checklist.getId());
      checklistInfo.setUsingStatus(checklist.getIsUsed());
      updateChecklist.add(checklistInfo);
    }

    for(AddedChecklistInfo checklist : memberChecklistRequest.getAddedGoodChecklist()) {
      ChecklistInfo newChecklistInfo = ChecklistInfo.builder()
              .id(null)
              .criteria(checklist.getCriteria())
              .isGood(true)
              .isUsed(checklist.getIsUsed())
              .build();
      updateChecklist.add(newChecklistInfo);
    }

    for(AddedChecklistInfo checklist : memberChecklistRequest.getAddedBadChecklist()) {
      ChecklistInfo newChecklistInfo = ChecklistInfo.builder()
              .id(null)
              .criteria(checklist.getCriteria())
              .isGood(false)
              .isUsed(checklist.getIsUsed())
              .build();
      updateChecklist.add(newChecklistInfo);
    }

    saveMemberChecklistPort.saveMemberChecklistWithCond(memberId, updateChecklist);
  }


  @Override
  public String deleteMember(UUID memberId) {
    deleteMemberPort.deleteMember(memberId);
    return "success";
  }
}

package com.dnd.bbok.member.application.service;
import com.dnd.bbok.member.application.port.in.request.ChecklistInfoRequest;
import com.dnd.bbok.member.application.port.in.request.CreateMemberChecklistRequest;
import com.dnd.bbok.member.application.port.in.request.EditMemberChecklistRequest;
import com.dnd.bbok.member.application.port.in.response.GetMemberChecklistResponse;
import com.dnd.bbok.member.application.port.in.response.GetMemberInfoResponse;

import com.dnd.bbok.member.application.port.in.usecase.CreateMemberChecklistUseCase;
import com.dnd.bbok.member.application.port.in.usecase.EditMemberChecklistUseCase;
import com.dnd.bbok.member.application.port.in.usecase.GetMemberChecklistQuery;
import com.dnd.bbok.member.application.port.in.usecase.GetMemberQuery;

import com.dnd.bbok.member.application.port.out.LoadMemberChecklistPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.application.port.out.SaveMemberChecklistPort;

import com.dnd.bbok.member.domain.ChecklistInfo;
import com.dnd.bbok.member.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dnd.bbok.member.domain.MemberChecklist;
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
    EditMemberChecklistUseCase {

  private final LoadMemberPort loadMemberPort;
  private final LoadMemberChecklistPort loadMemberChecklistPort;
  private final SaveMemberChecklistPort saveMemberChecklistPort;

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
      badChecklist.add(new ChecklistInfo(null, ele, false, true))
    );

    createMemberChecklistRequest.getGoodChecklist().forEach(ele ->
      goodChecklist.add(new ChecklistInfo(null, ele, true, true))
    );


    MemberChecklist memberChecklist = new MemberChecklist(goodChecklist, badChecklist);
    saveMemberChecklistPort.saveMemberChecklist(memberId, memberChecklist);
  }

  @Override
  public GetMemberChecklistResponse getMemberChecklistQuery(UUID memberId) {
    MemberChecklist memberChecklist = loadMemberChecklistPort.loadMemberChecklist(memberId);
    // log.info(memberChecklist.getGoodChecklist().get(0).getCriteria());
    // log.info(memberChecklist.getBadChecklist().get(0).getCriteria());
    return new GetMemberChecklistResponse(memberChecklist.getGoodChecklist(), memberChecklist.getBadChecklist()) ;
  }

  /**
   * member checklist 수정하기
   * EditMemberChecklistRequest - 사용자가 수정한 체크리스트들만 request body 에 담겨온다.
   */
  @Override
  public void edit(UUID memberId, EditMemberChecklistRequest memberChecklistRequest) {
    List<ChecklistInfo> newChecklist = new ArrayList<>();

    //수정하려는 체크리스트를 가져온다.
    List<ChecklistInfoRequest> updateChecklist = memberChecklistRequest.getChecklist();

    for(ChecklistInfoRequest checklist : updateChecklist) {
      //id가 있다면 바꾸려는 것이므로 해당 Id의 상태를 false로 바꾸고, criteria는 새롭게 저장해줘야 한다.
      if(checklist.getId() != null) {
        ChecklistInfo checklistInfo = loadMemberChecklistPort.loadOneById(checklist.getId());
        checklistInfo.setUsingStatus(false);
        newChecklist.add(checklistInfo);
      }
      //id를 null로 지정해도 기존 Id를 가져오는 이슈 발생. -> ChecklistInfo 필드값 변경
      ChecklistInfo newChecklistInfo = ChecklistInfo.builder()
          .id(null)
          .criteria(checklist.getCriteria())
          .isGood(memberChecklistRequest.isGood())
          .isUsed(true)
          .build();
      newChecklist.add(newChecklistInfo);
    }
    //good인지, bad인지에 따라 save하는 메서드를 만든다.
    saveMemberChecklistPort.saveMemberChecklistWithCond(memberId, newChecklist);
  }
}

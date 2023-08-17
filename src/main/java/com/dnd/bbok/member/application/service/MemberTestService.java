package com.dnd.bbok.member.application.service;

import com.dnd.bbok.member.application.port.in.*;
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
//TODO: 기존 구조에서 MemberService 지울 때, 이름 변경하기
public class MemberTestService implements GetMemberQuery, CreateMemberChecklistUseCase, GetMemberChecklistQuery {

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
      badChecklist.add(new ChecklistInfo(null, ele))
    );

    createMemberChecklistRequest.getGoodChecklist().forEach(ele ->
      goodChecklist.add(new ChecklistInfo(null, ele))
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
}

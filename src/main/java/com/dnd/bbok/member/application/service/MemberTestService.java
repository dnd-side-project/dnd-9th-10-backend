package com.dnd.bbok.member.application.service;

import com.dnd.bbok.member.application.port.in.GetMemberInfoResponse;
import com.dnd.bbok.member.application.port.in.GetMemberQuery;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.domain.Member;
import java.util.UUID;
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
public class MemberTestService implements GetMemberQuery {

  private final LoadMemberPort loadMemberPort;

  @Override
  public GetMemberInfoResponse getMember(UUID memberId) {
    Member member = loadMemberPort.loadById(memberId);
    return new GetMemberInfoResponse(member);
  }
}

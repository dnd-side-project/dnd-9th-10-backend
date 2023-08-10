package com.dnd.bbok.domain.member.service;

import com.dnd.bbok.domain.member.dto.response.MemberInfoResponseDto;
import com.dnd.bbok.domain.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberService memberService;

  public MemberInfoResponseDto getMember(UUID memberId) {
    Member member = memberService.getMemberById(memberId);
    return new MemberInfoResponseDto(member);
  }
}

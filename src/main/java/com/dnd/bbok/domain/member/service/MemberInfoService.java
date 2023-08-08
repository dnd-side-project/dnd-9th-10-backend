package com.dnd.bbok.domain.member.service;

import static com.dnd.bbok.global.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.dnd.bbok.domain.member.dto.response.MemberInfoResponseDto;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.exception.MemberNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

  private final MemberService memberService;

  public MemberInfoResponseDto getMember(UUID memberId) {
    Member member = memberService.getMemberById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    return new MemberInfoResponseDto(member);
  }
}

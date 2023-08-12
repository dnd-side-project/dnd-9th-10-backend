package com.dnd.bbok.domain.friend.service;

import com.dnd.bbok.domain.friend.dto.request.FriendRequestDto;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.service.MemberService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 친구 관련 서비스
 */
@Service
@RequiredArgsConstructor
public class MemberFriendUseCaseService {

  private final FriendService friendService;
  private final MemberService memberService;

  public void createFriendCharacter(UUID memberId, FriendRequestDto requestFriend) {
    Member member = memberService.getMemberById(memberId);
    //2. member가 활성화된 다른 친구를 가지고 있는지 체크해야 한다.
    friendService.checkOtherActiveFriend(member);
    friendService.saveFriend(requestFriend.toEntity(member));
  }
}

package com.dnd.bbok.domain.friend.service;

import com.dnd.bbok.domain.diary.service.DiaryEntityService;
import com.dnd.bbok.domain.friend.dto.response.FriendDto;
import com.dnd.bbok.domain.friend.dto.response.FriendsDto;
import com.dnd.bbok.domain.friend.entity.Friend;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Friend, Diary 관련 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryFriendUseCaseService {

  private final FriendService friendService;
  private final IconService iconService;
  private final DiaryEntityService diaryEntityService;

  public FriendsDto getFriends(UUID memberId) {

    List<Friend> friendsOfMember = friendService.findFriendsOfMember(memberId);

    List<FriendDto> friends = friendsOfMember.stream()
        .map(each -> {
          String iconUrl = iconService.getIconUrl(each.getBbok());
          int countDiary = diaryEntityService.countDiariesByFriendId(each.getId());
          return new FriendDto(each, iconUrl, countDiary);
        })
        .collect(Collectors.toList());

    return new FriendsDto(friends);
  }


  //TODO: 점수 받으면 friend에 업데이트하는 서비스(friendId, score)
  @Transactional
  public void updateScore(Long friendId, Long score) {
    Friend friend = friendService.getFriend(friendId);
    friend.changeFriendScore(score);
  }
}

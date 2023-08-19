package com.dnd.bbok.friend.application.service;

import com.dnd.bbok.domain.diary.service.DiaryEntityService;

import com.dnd.bbok.friend.application.port.in.request.FriendRequestInfo;
import com.dnd.bbok.friend.application.port.in.response.FriendInfo;
import com.dnd.bbok.friend.application.port.in.response.FriendGroupInfo;
import com.dnd.bbok.friend.application.port.in.usecase.GetFriendsQuery;
import com.dnd.bbok.friend.application.port.in.usecase.RegisterFriendUseCase;

import com.dnd.bbok.friend.application.port.out.FriendValidatorPort;
import com.dnd.bbok.friend.application.port.out.LoadFriendPort;
import com.dnd.bbok.friend.application.port.out.SaveFriendPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;

import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.member.domain.Member;

import com.dnd.bbok.infra.s3.service.S3Downloader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendTestService implements GetFriendsQuery, RegisterFriendUseCase {

  private final S3Downloader s3Downloader;
  private final LoadFriendPort loadFriendPort;
  private final LoadMemberPort loadMemberPort;
  private final FriendValidatorPort friendValidatorPort;
  private final SaveFriendPort saveFriendPort;

  //TODO: 이후에 수정해야 하는 부분.
  private final DiaryEntityService diaryEntityService;

  @Override
  public FriendGroupInfo getFriends(UUID memberID) {
    List<Friend> friends = loadFriendPort.getByMemberId(memberID);
    List<FriendInfo> friendsInfo = friends.stream()
        .map(friend -> {
          String iconUrl = s3Downloader.getIconUrl(friend.getBbok().getIconFile());
          int countDiary = diaryEntityService.countDiariesByFriendId(friend.getId());
          return new FriendInfo(friend, iconUrl, countDiary);
        })
        .collect(Collectors.toList());
    return new FriendGroupInfo(friendsInfo);
  }

  @Override
  public void createFriendCharacter(UUID memberId, FriendRequestInfo friend) {
    //1. member가 있는지 확인한다.
    Member member = loadMemberPort.loadById(memberId);

    //2. member가 다른 active한 친구가 있는지 체크한다.
    friendValidatorPort.checkOtherActiveFriend(member);

    //3. request 내용을 검증한다.
    friendValidatorPort.validateNaming(friend.getName());

    //4. 무사히 통과되면 friend를 저장한다.
    saveFriendPort.saveFriend(member.getId(), friend);
  }
}

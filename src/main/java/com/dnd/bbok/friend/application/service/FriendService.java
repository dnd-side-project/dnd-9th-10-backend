package com.dnd.bbok.friend.application.service;

import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import com.dnd.bbok.friend.application.port.in.request.CreateFriendRequest;
import com.dnd.bbok.friend.application.port.in.request.UpdateFriendRequest;
import com.dnd.bbok.friend.application.port.in.response.GetFriendResponse;
import com.dnd.bbok.friend.application.port.in.response.GetFriendGroupResponse;
import com.dnd.bbok.friend.application.port.in.usecase.EditFriendUseCase;
import com.dnd.bbok.friend.application.port.in.usecase.GetFriendsQuery;
import com.dnd.bbok.friend.application.port.in.usecase.RegisterFriendUseCase;

import com.dnd.bbok.friend.application.port.out.FriendValidatorPort;
import com.dnd.bbok.friend.application.port.out.LoadFriendPort;
import com.dnd.bbok.friend.application.port.out.LoadIconPort;
import com.dnd.bbok.friend.application.port.out.SaveFriendPort;
import com.dnd.bbok.friend.domain.BbokCharacter;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendService implements GetFriendsQuery, RegisterFriendUseCase,
    EditFriendUseCase {

  private final S3Downloader s3Downloader;
  private final LoadFriendPort loadFriendPort;
  private final LoadMemberPort loadMemberPort;
  private final LoadIconPort loadIconPort;
  private final FriendValidatorPort friendValidatorPort;
  private final SaveFriendPort saveFriendPort;
  private final LoadDiaryPort loadDiaryPort;

  @Override
  public GetFriendGroupResponse getFriends(UUID memberID) {
    List<Friend> friends = loadFriendPort.getByMemberId(memberID);
    List<GetFriendResponse> friendsInfo = friends.stream()
        .map(friend -> {
          String iconUrl = s3Downloader.getIconUrl(friend.getBbok().getIconFile());
          int countDiary = loadDiaryPort.countDiariesByFriendId(friend.getId());
          return new GetFriendResponse(friend, iconUrl, countDiary);
        })
        .collect(Collectors.toList());
    return new GetFriendGroupResponse(friendsInfo);
  }

  @Override
  public void createFriendCharacter(UUID memberId, CreateFriendRequest friend) {
    //1. member가 있는지 확인한다. (없으면, MEMBER_NOT_FOUND 처리)
    Member member = loadMemberPort.loadById(memberId);

    //2. member가 다른 active한 친구가 있는지 체크한다.
    friendValidatorPort.checkOtherActiveFriend(member);

    //3. request 내용을 검증한다.
    friendValidatorPort.validateNaming(friend.getName());

    //요청한 리퀘바디의 캐릭터를 꺼내온다.
    BbokCharacter character = loadIconPort.getCharacter(friend.getCharacter());

    //새로운 친구를 생성한다.
    Friend newFriend = Friend.builder()
        .friendScore(0L)
        .active(true)
        .name(friend.getName())
        .bbok(character)
        .build();

    //4. friend를 저장한다.
    saveFriendPort.saveFriend(member, newFriend);
  }

  @Transactional
  @Override
  public void editName(UUID memberId, Long friendId, UpdateFriendRequest updateFriendRequest) {
    Member member = loadMemberPort.loadById(memberId);

    //1. 현재 active한 친구인지 확인한다.
    Friend friend = friendValidatorPort.isActiveFriend(memberId, friendId);

    //2. 요청한 이름이 조건에 부합한지 확인한다.
    friendValidatorPort.validateNaming(updateFriendRequest.getName());

    friend.setName(updateFriendRequest.getName());

    //3. request 내용 반영하기
    saveFriendPort.saveFriend(member, friend);
  }

  @Override
  public void editStatus(UUID memberId, Long friendId) {
    Member member = loadMemberPort.loadById(memberId);

    //1. 현재 active한 친구인지 확인한다.
    Friend friend = friendValidatorPort.isActiveFriend(memberId, friendId);
    friend.setStatus();

    //2. friend의 상태를 비활성화로 업데이트한다.
    saveFriendPort.saveFriend(member, friend);
  }
}

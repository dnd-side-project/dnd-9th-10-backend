package com.dnd.bbok.friend.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.FRIEND_IS_NOT_ACTIVE;
import static com.dnd.bbok.global.exception.ErrorCode.FRIEND_NOT_FOUND;
import static com.dnd.bbok.global.exception.ErrorCode.INVALID_FRIEND_NAME;
import static com.dnd.bbok.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.dnd.bbok.global.exception.ErrorCode.OTHER_FRIEND_ALREADY_ACTIVE;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.mapper.FriendMapper;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTestRepository;
import com.dnd.bbok.friend.application.port.in.request.FriendInfoRequest;
import com.dnd.bbok.friend.application.port.in.request.UpdateFriendRequest;
import com.dnd.bbok.friend.application.port.out.FriendValidatorPort;
import com.dnd.bbok.friend.application.port.out.LoadFriendPort;
import com.dnd.bbok.friend.application.port.out.SaveFriendPort;
import com.dnd.bbok.friend.application.port.out.UpdateFriendPort;
import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberMapper;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberTestRepository;
import com.dnd.bbok.member.domain.Member;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FriendPersistenceAdapter
    implements LoadFriendPort, FriendValidatorPort, SaveFriendPort, UpdateFriendPort {

  private final FriendTestRepository friendTestRepository;
  private final MemberTestRepository memberRepository;
  private final FriendMapper friendMapper;
  private final MemberMapper memberMapper;

  @Override
  public List<Friend> getByMemberId(UUID memberId) {
    List<FriendEntity> friends = friendTestRepository.findAllFriends(memberId);
    return friendMapper.convertEntitiesToDomain(friends);
  }

  @Override
  public void checkOtherActiveFriend(Member member) {
    List<FriendEntity> otherFriends = friendTestRepository.findAllFriends(member.getId());
    if(otherFriends.stream().anyMatch(FriendEntity::isActive)) {
      throw new BusinessException(OTHER_FRIEND_ALREADY_ACTIVE);
    }
  }

  @Override
  public void validateNaming(String friendName) {
    final String namingCond = "^[a-zA-Z0-9가-힣]{1,12}$";
    if(!friendName.matches(namingCond)) {
      throw new BusinessException(INVALID_FRIEND_NAME);
    }
  }

  @Override
  public Friend isActiveFriend(UUID memberId, Long friendId) {
    FriendEntity friend = friendTestRepository.findFriendById(friendId)
        .orElseThrow(() -> new BusinessException(FRIEND_NOT_FOUND));
    if(!friend.isActive()) {
      throw new BusinessException(FRIEND_IS_NOT_ACTIVE);
    }
    Member memberDomain = memberMapper.toDomain(friend.getMember());
    //member 도메인을 생성해주는 MemberMapper가 필요하다.
    return friendMapper.toDomain(memberDomain, friend);
  }

  @Override
  public void saveFriend(UUID memberId, FriendInfoRequest friendRequest) {
    MemberEntity member = memberRepository.findById(memberId)
        .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    FriendEntity friendEntity = friendMapper.convertDtoToEntity(member, friendRequest);
    friendTestRepository.save(friendEntity);
  }

  @Override
  public void updateFriend(Friend friend, UpdateFriendRequest friendRequest) {
    MemberEntity member = memberRepository.findById(friend.getMember().getId())
        .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    FriendEntity friendEntity = friendMapper.toEntity(member, friend);
    friendEntity.changeFriendName(friendRequest.getName());
    friendTestRepository.save(friendEntity);
  }

  @Override
  public void updateStatus(UUID memberId, Friend friend) {
    MemberEntity member = memberRepository.findById(memberId)
        .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    FriendEntity friendEntity = friendMapper.toEntity(member, friend);
    friendEntity.deactivateFriend();
    friendTestRepository.save(friendEntity);
  }
}

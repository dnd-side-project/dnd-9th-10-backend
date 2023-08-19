package com.dnd.bbok.friend.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.INVALID_FRIEND_NAME;
import static com.dnd.bbok.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.dnd.bbok.global.exception.ErrorCode.OTHER_FRIEND_ALREADY_ACTIVE;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.mapper.FriendMapper;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTestRepository;
import com.dnd.bbok.friend.application.port.in.request.FriendRequestInfo;
import com.dnd.bbok.friend.application.port.out.FriendValidatorPort;
import com.dnd.bbok.friend.application.port.out.LoadFriendPort;
import com.dnd.bbok.friend.application.port.out.SaveFriendPort;
import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
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
    implements LoadFriendPort, FriendValidatorPort, SaveFriendPort {

  private final FriendTestRepository friendRepository;
  private final MemberTestRepository memberRepository;
  private final FriendMapper friendMapper;

  @Override
  public List<Friend> getByMemberId(UUID memberId) {
    List<FriendEntity> friends = friendRepository.findAllFriends(memberId);
    return friendMapper.convertEntitiesToDomain(friends);
  }

  @Override
  public void checkOtherActiveFriend(Member member) {
    List<FriendEntity> otherFriends = friendRepository.findAllFriends(member.getId());
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
  public void saveFriend(UUID memberId, FriendRequestInfo friendRequest) {
    MemberEntity member = memberRepository.findById(memberId)
        .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    FriendEntity friendEntity = friendMapper.convertDtoToEntity(member, friendRequest);
    friendRepository.save(friendEntity);
  }
}

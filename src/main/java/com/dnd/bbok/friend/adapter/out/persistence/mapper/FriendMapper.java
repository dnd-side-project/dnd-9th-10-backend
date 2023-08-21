package com.dnd.bbok.friend.adapter.out.persistence.mapper;

import static com.dnd.bbok.friend.domain.BbokCharacter.*;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.application.port.in.request.CreateFriendRequest;
import com.dnd.bbok.friend.domain.BbokCharacter;
import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FriendMapper {

  public List<Friend> convertEntitiesToDomain(List<FriendEntity> friends) {
    return friends.stream()
                  .map(friend -> Friend.builder()
                                       .id(friend.getId())
                                       .name(friend.getName())
                                       .bbok(friend.getBbok())
                                       .friendScore(friend.getFriendScore())
                                       .createdAt(friend.getCreatedAt())
                                       .active(friend.isActive())
                                       .build())
                  .collect(Collectors.toList());
  }

  public FriendEntity convertDtoToEntity(MemberEntity member, CreateFriendRequest friendRequest) {
    BbokCharacter friendCharacter =
        (friendRequest.getCharacter().equals("CACTUS")) ? SIDE_CACTUS : SIDE_HEDGEHOG;

    return FriendEntity.builder()
        .active(true)
        .name(friendRequest.getName())
        .bbok(friendCharacter)
        .friendScore(0L)
        .member(member)
        .build();
  }

  public Friend toDomain(Member member, FriendEntity friend) {
    return Friend.builder()
        .id(friend.getId())
        .name(friend.getName())
        .friendScore(friend.getFriendScore())
        .createdAt(friend.getCreatedAt())
        .bbok(friend.getBbok())
        .active(friend.isActive())
        .member(member)
        .build();
  }

  public FriendEntity toEntity(MemberEntity member, Friend friend) {
    return FriendEntity.builder()
        .createdAt(friend.getCreatedAt())
        .id(friend.getId())
        .name(friend.getName())
        .bbok(friend.getBbok())
        .active(friend.isActive())
        .friendScore(friend.getFriendScore())
        .member(member)
        .build();
  }
}

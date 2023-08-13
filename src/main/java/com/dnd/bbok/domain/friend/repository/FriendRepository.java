package com.dnd.bbok.domain.friend.repository;

import com.dnd.bbok.domain.friend.entity.Friend;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long> {

  @Query("select f from Friend f where f.member.id = :memberId")
  List<Friend> findAllFriends(@Param("memberId") UUID memberId);

  //TODO: 승훈님이 요청하신 친구 Id로 친구를 조회하는 메서드 구현
  @Query("select f from Friend f where f.id = :friendId")
  Friend findFriendById(@Param("friendId") Long friendId);

}

package com.dnd.bbok.domain.friend.repository;

import com.dnd.bbok.domain.friend.entity.Friend;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long> {

  @Query("select f from Friend f where f.member.id = :memberId")
  List<Friend> findOtherFriend(@Param("memberId") UUID memberId);
}

package com.dnd.bbok.friend.adapter.out.persistence.repository;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

  @Query("select f from FriendEntity f where f.member.id = :memberId")
  List<FriendEntity> findAllFriends(@Param("memberId") UUID memberId);

  @Query("select f from FriendEntity f where f.id = :friendId")
  Optional<FriendEntity> findById(@Param("friendId") Long friendId);
}

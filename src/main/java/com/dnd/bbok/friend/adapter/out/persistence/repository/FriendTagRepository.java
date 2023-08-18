package com.dnd.bbok.friend.adapter.out.persistence.repository;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendTagRepository extends JpaRepository<FriendTagEntity, Long> {
    List<FriendTagEntity> findAllByFriendId(Long friendId);

}

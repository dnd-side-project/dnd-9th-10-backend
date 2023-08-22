package com.dnd.bbok.friend.adapter.out.persistence.repository;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendTagRepository extends JpaRepository<FriendTagEntity, Long> {
    List<FriendTagEntity> findAllByFriendId(Long friendId);

}

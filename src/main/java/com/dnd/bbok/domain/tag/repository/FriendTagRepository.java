package com.dnd.bbok.domain.tag.repository;

import com.dnd.bbok.domain.tag.entity.FriendTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendTagRepository extends JpaRepository<FriendTag, Long> {
    List<FriendTag> findAllByFriendId(Long friendId);
}

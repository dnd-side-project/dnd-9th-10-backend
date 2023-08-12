package com.dnd.bbok.domain.diary.repository;

import com.dnd.bbok.domain.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}

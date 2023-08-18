package com.dnd.bbok.friend.adapter.out.persistence.repository;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendTestRepository extends JpaRepository<FriendEntity, Long> {

}

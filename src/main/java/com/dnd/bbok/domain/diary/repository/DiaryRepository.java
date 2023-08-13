package com.dnd.bbok.domain.diary.repository;

import com.dnd.bbok.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByFriendId(Long friendId);
}

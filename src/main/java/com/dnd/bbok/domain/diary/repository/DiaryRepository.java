package com.dnd.bbok.domain.diary.repository;

import com.dnd.bbok.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByFriendId(Long friendId);

    @Query("select count(*) from Diary d where d.friend.id = :friendId")
    int countDiaries(@Param("friendId") Long friendId);
}

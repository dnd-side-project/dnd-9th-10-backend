package com.dnd.bbok.domain.diary.repository;

import com.dnd.bbok.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}

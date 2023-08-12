package com.dnd.bbok.domain.tag.repository;

import com.dnd.bbok.domain.tag.entity.DiaryTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {
}

package com.dnd.bbok.domain.diary.repository;

import com.dnd.bbok.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

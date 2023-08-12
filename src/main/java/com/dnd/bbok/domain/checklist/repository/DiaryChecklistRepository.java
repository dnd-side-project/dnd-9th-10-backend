package com.dnd.bbok.domain.checklist.repository;

import com.dnd.bbok.domain.checklist.entity.DiaryChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryChecklistRepository extends JpaRepository<DiaryChecklist, Long> {

}

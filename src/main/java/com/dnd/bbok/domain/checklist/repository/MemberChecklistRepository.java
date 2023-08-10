package com.dnd.bbok.domain.checklist.repository;

import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChecklistRepository extends JpaRepository<MemberChecklist, Long> {
}

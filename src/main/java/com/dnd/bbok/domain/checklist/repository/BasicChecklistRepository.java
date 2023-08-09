package com.dnd.bbok.domain.checklist.repository;


import com.dnd.bbok.domain.checklist.entity.BasicChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicChecklistRepository extends JpaRepository<BasicChecklist, Long> {
}

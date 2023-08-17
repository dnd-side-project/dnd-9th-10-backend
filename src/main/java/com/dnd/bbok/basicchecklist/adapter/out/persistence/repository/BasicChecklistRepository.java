package com.dnd.bbok.basicchecklist.adapter.out.persistence.repository;


import com.dnd.bbok.basicchecklist.adapter.out.persistence.entity.BasicChecklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicChecklistRepository extends JpaRepository<BasicChecklistEntity, Long> {
}

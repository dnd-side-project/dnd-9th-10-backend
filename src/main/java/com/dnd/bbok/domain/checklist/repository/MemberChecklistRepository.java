package com.dnd.bbok.domain.checklist.repository;

import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberChecklistRepository extends JpaRepository<MemberChecklist, Long> {
    List<MemberChecklist> findByIdIn(List<Long> ids);

    @Query("select mc from MemberChecklist mc where mc.isUsed = true and mc.member.id = :memberId")
    List<MemberChecklist> findByChecklistInUsing(@Param("memberId") UUID memberId);
}

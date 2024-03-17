package com.dnd.bbok.member.adapter.out.persistence.repository;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberChecklistRepository extends JpaRepository<MemberChecklistEntity, Long> {
    List<MemberChecklistEntity> findByIdIn(List<Long> ids);

    @Query("select mc from MemberChecklistEntity mc where mc.isUsed = true and mc.member.id = :memberId")
    List<MemberChecklistEntity> findByChecklistInUsing(@Param("memberId") UUID memberId);

    @Query("select mc from MemberChecklistEntity mc where mc.member.id = :memberId")
    List<MemberChecklistEntity> findByMemberId(@Param("memberId") UUID memberId);
}

package com.dnd.bbok.member.adapter.out.persistence.repository;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//TODO: 기존 구조에서 완전히 변경될때, MemberRepository로 변경해주기
public interface MemberTestRepository extends JpaRepository<MemberEntity, UUID> {

  @Query("select m from MemberEntity m where m.id = :memberId")
  Optional<MemberEntity> findById(@Param("memberId") UUID memberId);
}

package com.dnd.bbok.member.adapter.out.persistence.repository;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {

  @Query("select m from MemberEntity m where m.id = :id")
  Optional<MemberEntity> findById(@Param("id") UUID id);

  @Query("select m from MemberEntity m where m.userNumber = :userNumber")
  Optional<MemberEntity> findByUserNumber(@Param("userNumber") String userNumber);

}

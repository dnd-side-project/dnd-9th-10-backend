package com.dnd.bbok.saying.adapter.out.persistence.repository;

import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SayingRepository extends JpaRepository<SayingEntity, Long> {

  @Query("select s from SayingEntity s where s.id = :sayingId")
  Optional<SayingEntity> findSayingById(@Param("sayingId") Long sayingId);

}

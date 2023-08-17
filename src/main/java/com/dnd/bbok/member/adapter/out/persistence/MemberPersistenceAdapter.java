package com.dnd.bbok.member.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.*;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberChecklistMapper;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberMapper;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberChecklistRepository;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberTestRepository;
import com.dnd.bbok.member.application.port.out.LoadMemberChecklistPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.application.port.out.SaveMemberChecklistPort;
import com.dnd.bbok.member.domain.Member;

import java.util.List;
import java.util.UUID;

import com.dnd.bbok.member.domain.MemberChecklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * port 인터페이스들의 구현체이다.
 * 1. 요청으로부터 입력 추출한다.
 * 2. 데이터 포맷에 맞게 매핑한다. (MemberMapper 의존성을 받아서 활용할 예정)
 * 3. 입력을 DB로 보낸다.
 * 4. DB 출력을 애플리케이션 포맷에 맞게 매핑한다.
 * 5. 출력을 반환한다.
 */

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, LoadMemberChecklistPort, SaveMemberChecklistPort {

  private final MemberTestRepository memberTestRepository;
  private final MemberChecklistRepository memberChecklistRepository;
  private final MemberMapper memberMapper;
  private final MemberChecklistMapper memberChecklistMapper;

  @Override
  public Member loadById(UUID memberId) {
    MemberEntity member = memberTestRepository.findById(memberId)
        .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    return memberMapper.toDomain(member);
  }

  @Override
  public MemberChecklist loadMemberChecklist(UUID memberId) {
    return memberChecklistMapper.toDomain(memberChecklistRepository.findByChecklistInUsing(memberId));
  }

  @Override
  public void saveMemberChecklist(UUID memberId, MemberChecklist memberChecklist) {
    MemberEntity memberEntity = memberTestRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    List<MemberChecklistEntity> memberChecklistEntities = memberChecklistMapper.toEntity(memberChecklist, memberEntity);
    memberChecklistRepository.saveAll(memberChecklistEntities);
  }
}

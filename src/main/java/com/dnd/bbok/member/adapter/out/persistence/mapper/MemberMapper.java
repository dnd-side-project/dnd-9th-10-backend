package com.dnd.bbok.member.adapter.out.persistence.mapper;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.domain.Member;
import org.springframework.stereotype.Component;

/**
 * DB에 쿼리를 날리거나, 변경하는데 사용할 수 있는 포맷으로 입력모델 매핑
 */
@Component
public class MemberMapper {

  public Member toDomain(MemberEntity member) {
    return Member.builder()
        .id(member.getId())
        .role(member.getRole())
        .profileUrl(member.getProfileUrl())
        .userNumber(member.getUserNumber())
        .username(member.getUsername())
        .oAuth2Provider(member.getOauth2Provider())
        .build();
  }

  public MemberEntity toEntity(Member member) {
    return MemberEntity.builder()
        .userNumber(member.getUserNumber())
        .role(member.getRole())
        .oAuth2Provider(member.getOAuth2Provider())
        .profileUrl(member.getProfileUrl())
        .username(member.getUsername())
        .build();
  }
}

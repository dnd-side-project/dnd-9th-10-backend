package com.dnd.bbok.domain.member.service;

import static com.dnd.bbok.global.exception.ErrorCode.*;

import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.entity.OAuth2Provider;
import com.dnd.bbok.domain.member.entity.Role;
import com.dnd.bbok.domain.member.repository.MemberRepository;
import com.dnd.bbok.global.exception.BusinessException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public Member saveGuestMember() {
    return memberRepository.save(createGuestMember());
  }

  private Member createGuestMember() {
    return Member.builder()
        .userNumber(
            String.format("%s#%s", OAuth2Provider.GUEST, UUID.randomUUID().toString().split("-")[0])
        )
        .username("NONE")
        .role(Role.ROLE_GUEST)
        .profileUrl("https://i.ibb.co/34SNTwm/image.png")
        .oauth2Provider(OAuth2Provider.GUEST)
        .build();
  }

  public Optional<Member> getMemberByUserNumber(String username) {
    return memberRepository.findByUsername(username);
  }

  public Member saveInfo(Member member) {
    return memberRepository.save(member);
  }

  public Member getMemberById(UUID uuid) {
    log.info("해당 uuid를 가진 멤버를 찾습니다.");
    return memberRepository.findById(uuid)
        .orElseThrow(()->new BusinessException(MEMBER_NOT_FOUND));
  }
}

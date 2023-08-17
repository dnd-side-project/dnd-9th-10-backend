package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.Member;

public interface SaveMemberPort {
  Member saveMember(Member member);

}

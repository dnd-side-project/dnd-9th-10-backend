package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.Member;
import java.util.UUID;

public interface LoadMemberPort {

  Member loadById(UUID memberId);

  Member loadByUserNumber(String userNumber);

}

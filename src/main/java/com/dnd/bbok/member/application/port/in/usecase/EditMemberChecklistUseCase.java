package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.member.application.port.in.request.EditMemberChecklistRequest;
import java.util.UUID;

public interface EditMemberChecklistUseCase {

  void edit(UUID memberId, EditMemberChecklistRequest memberChecklistRequest);

}

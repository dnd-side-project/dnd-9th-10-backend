package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.application.port.in.request.ChecklistInfoRequest;
import java.util.List;
import java.util.UUID;

public interface EditMemberChecklistPort {

  void editChecklist(UUID memberId, List<ChecklistInfoRequest> good, List<ChecklistInfoRequest> bad);

}

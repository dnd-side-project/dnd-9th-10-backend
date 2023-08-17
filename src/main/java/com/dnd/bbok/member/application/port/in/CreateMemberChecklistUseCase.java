package com.dnd.bbok.member.application.port.in;

import java.util.UUID;

public interface CreateMemberChecklistUseCase {
    void createMemberChecklist(UUID memberId, CreateMemberChecklistRequest createMemberChecklistRequest);
}

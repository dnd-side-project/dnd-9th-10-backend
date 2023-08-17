package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.member.application.port.in.request.CreateMemberChecklistRequest;

import java.util.UUID;

public interface CreateMemberChecklistUseCase {
    void createMemberChecklist(UUID memberId, CreateMemberChecklistRequest createMemberChecklistRequest);
}

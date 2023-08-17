package com.dnd.bbok.basicchecklist.application.service;

import com.dnd.bbok.basicchecklist.application.port.in.usecase.GetBasicChecklistQuery;
import com.dnd.bbok.basicchecklist.application.port.in.response.GetBasicChecklistResponse;
import com.dnd.bbok.basicchecklist.application.port.out.LoadBasicChecklistPort;
import com.dnd.bbok.basicchecklist.domain.BasicChecklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicChecklistService implements GetBasicChecklistQuery {
    private final LoadBasicChecklistPort loadBasicChecklistPort;
    @Override
    public GetBasicChecklistResponse getBasicChecklist() {
        BasicChecklist basicChecklist = loadBasicChecklistPort.load();
        return new GetBasicChecklistResponse(basicChecklist.getGoodChecklist(), basicChecklist.getBadChecklist());
    }
}

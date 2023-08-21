package com.dnd.bbok.basicchecklist.adapter.in.web;

import com.dnd.bbok.basicchecklist.application.port.in.usecase.GetBasicChecklistQuery;
import com.dnd.bbok.basicchecklist.application.port.in.response.GetBasicChecklistResponse;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/checklist")
@Api(tags = "체크리스트 관련 컨트롤러")
public class BasicChecklistController {
    private final GetBasicChecklistQuery getBasicChecklistQuery;

    @ApiOperation(value = "기본 체크리스트 제공")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public ResponseEntity<DataResponse<GetBasicChecklistResponse>> getBasicChecklist() {
        GetBasicChecklistResponse basicChecklist = getBasicChecklistQuery.getBasicChecklist();
        return new ResponseEntity<>(
                DataResponse.of(HttpStatus.OK, "기본 체크리스트 제공 성공", basicChecklist), HttpStatus.OK);
    }
}

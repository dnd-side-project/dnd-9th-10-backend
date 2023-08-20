package com.dnd.bbok.diary.adapter.in.web;

import com.dnd.bbok.diary.application.port.in.response.GetDiariesResponse;
import com.dnd.bbok.diary.application.port.in.response.GetDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiariesQuery;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiaryQuery;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class GetDiariesController {
    private final GetDiariesQuery getDiariesQuery;
    private final GetDiaryQuery getDiaryQuery;

    @ApiOperation(value = "일기 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary")
    public ResponseEntity<DataResponse<GetDiariesResponse>> getDiaries(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @Parameter(name = "offset", in = ParameterIn.QUERY, description = "목록 오프셋") @RequestParam(value = "offset", required = false) Integer offset,
            @Parameter(name = "order", in = ParameterIn.QUERY, description = "시간 정렬 기준") @RequestParam(value = "order", required = false) String order,
            @Parameter(name = "q", in = ParameterIn.QUERY, description = "검색어") @RequestParam(value = "q", required = false) String keyword,
            @Parameter(name = "tag", in = ParameterIn.QUERY, description = "태그") @RequestParam(value = "tag", required = false) String tag
    ) {
        // TODO 남의 일기를 조회 요청한 경우 에러처러??
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 목록 조회 성공", getDiariesQuery.getDiariesQuery(id, offset, order, keyword, tag)), HttpStatus.OK);
    }

    @ApiOperation(value = "일기 상세 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary/detail/{id}")
    public ResponseEntity<DataResponse<GetDiaryResponse>> getDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") Long id
    ) {
        // TODO 남의 일기를 조회 요청한 경우 에러처러??
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 조회 성공", getDiaryQuery.getDiary(id)), HttpStatus.OK);
    }
}

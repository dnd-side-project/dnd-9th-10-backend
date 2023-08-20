package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.friend.application.port.in.response.GetFriendTagsResponse;
import com.dnd.bbok.friend.application.port.in.usecase.GetFriendTagsQuery;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "친구 관련 컨트롤러")
public class GetFriendTagsController {
    private final GetFriendTagsQuery getFriendTagsQuery;

    @ApiOperation("태그 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/tag")
    public ResponseEntity<DataResponse<GetFriendTagsResponse>> getTags(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "태그 목록 조회 성공", getFriendTagsQuery.getTags(id)), HttpStatus.OK);
    }
}

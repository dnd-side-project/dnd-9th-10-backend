package com.dnd.bbok.diary.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import java.util.List;

@Getter
public class GetDiariesResponse {

    @ApiModelProperty("일화 목록")
    private final List<GetDiaryResponse> diaries;

    @ApiModelProperty("시작 offset")
    private final int offset;

    @ApiModelProperty("페이지 당 담을 수 있는 최대 용량의 데이터 개수")
    private final int pageNumber;

    @ApiModelProperty("현재 페이지 번호 (0부터 시작)")
    private final int pageSize = 20;

    @ApiModelProperty("전체 요소 개수 (필터링에 만족하는)")
    private final int totalPages;

    @ApiModelProperty("전체 페이지의 개수 (필터링에 만족하는)")
    private final int totalElements;

    @ApiModelProperty("현재 페이지에 담긴 데이터 개수")
    private final int numberOfElements;

    public GetDiariesResponse(List<GetDiaryResponse> diaries) {
        this.diaries = diaries;
        this.offset = 0;
        this.pageNumber = 0;
        this.totalElements = 1;
        this.totalPages = 1;
        this.numberOfElements = diaries.size();
    }
}

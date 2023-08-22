package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.diary.domain.Diary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetDiariesResponse {

    @ApiModelProperty("일화 목록")
    private final List<GetDiaryResponse> diaries;

    @ApiModelProperty("시작 offset")
    private final long offset;

    @ApiModelProperty("페이지 당 담을 수 있는 최대 용량의 데이터 개수")
    private final int pageNumber;

    @ApiModelProperty("현재 페이지 번호 (0부터 시작)")
    private final int pageSize;

    @ApiModelProperty("전체 요소 개수 (필터링에 만족하는)")
    private final int totalPages;

    @ApiModelProperty("전체 페이지의 개수 (필터링에 만족하는)")
    private final long totalElements;

    @ApiModelProperty("현재 페이지에 담긴 데이터 개수")
    private final int numberOfElements;

    public GetDiariesResponse(Page<Diary> diaries) {
        this.diaries = diaries.getContent().stream().map(GetDiaryResponse::new).collect(Collectors.toList());
        this.numberOfElements = diaries.getNumberOfElements();
        this.offset = diaries.getPageable().getOffset();
        this.pageNumber = diaries.getPageable().getPageNumber();
        this.pageSize = diaries.getPageable().getPageSize();
        this.totalElements = diaries.getTotalElements();
        this.totalPages = diaries.getTotalPages();
    }
}

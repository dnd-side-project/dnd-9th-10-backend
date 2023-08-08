package com.dnd.bbok.domain.diary.dto.request;

import com.dnd.bbok.domain.diary.entity.Emoji;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class DiaryRequestDto {
    @ApiModelProperty(value = "사용한 이모지")
    private Emoji emoji;

    @ApiModelProperty(value = "일기 날짜")
    private LocalDate date;

    @ApiModelProperty(value = "일기 내용")
    private String content;

    @ApiModelProperty(value = "태그 목록")
    private ArrayList<String> tags;

    @ApiModelProperty(value = "체크한 적합 체크리스트 id 배열")
    private ArrayList<String> goodChecklist;

    @ApiModelProperty(value = "체크한 부적합 체크리스트 id 배열")
    private ArrayList<String> badChecklist;
}

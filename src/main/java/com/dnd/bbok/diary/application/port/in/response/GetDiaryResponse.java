package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.domain.Emoji;
import com.dnd.bbok.diary.domain.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetDiaryResponse {
    @ApiModelProperty(value = "일기 고유 ID")
    private final Long id;

    @ApiModelProperty(value = "일기에 사용한 이모지")
    private final Emoji emoji;

    @ApiModelProperty(value = "이모지 다운로드 url")
    private final String emojiUrl;

    @ApiModelProperty(value = "일기 작성 날짜")
    private final LocalDate date;

    @ApiModelProperty(value = "일기 내용")
    private final String content;

    @ApiModelProperty(value = "일기 태그 목록")
    private final List<String> tags;

    @ApiModelProperty(value = "적합 체크리스트")
    private final List<DiaryChecklistResponse> goodChecklist;

    @ApiModelProperty(value = "부적합 체크리스트")
    private final List<DiaryChecklistResponse> badChecklist;

    @ApiModelProperty(value = "스티커 JSON")
    private final String sticker;

    public GetDiaryResponse(Diary diary) {

        this.id = diary.getId();
        this.emoji = diary.getEmoji();
        this.date = diary.getDiaryDate();
        this.content = diary.getContents();
        this.sticker = diary.getSticker();

        this.tags = diary.getTags().stream().map(Tag::getTag).collect(Collectors.toList());

        this.goodChecklist = new ArrayList<>();
        this.badChecklist = new ArrayList<>();
        diary.getDiaryChecklist().stream().filter(DiaryChecklist::getIsGood).forEach(ele -> this.goodChecklist.add(new DiaryChecklistResponse(ele.getMemberChecklistId(), ele.getCriteria(), ele.getIsChecked())));
        diary.getDiaryChecklist().stream().filter(ele -> !ele.getIsGood()).forEach(ele -> this.badChecklist.add(new DiaryChecklistResponse(ele.getMemberChecklistId(), ele.getCriteria(), ele.getIsChecked())));

        // TODO 수정필요
        this.emojiUrl = "https://i.ibb.co/zbHrDVm/angry.png";
    }

    @Getter
    public static class DiaryChecklistResponse {
        @ApiModelProperty(value = "Member Checklist ID")
        private final Long id;
        private final String criteria;
        private final Boolean isChecked;

        public DiaryChecklistResponse(Long id, String criteria, boolean isChecked) {
            this.id = id;
            this.criteria = criteria;
            this.isChecked = isChecked;
        }
    }
}

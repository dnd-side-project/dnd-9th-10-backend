package com.dnd.bbok.domain.diary.dto.response;

import com.dnd.bbok.domain.diary.entity.Emoji;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DiaryDto {
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
    private final List<DiaryChecklistDto> goodChecklist;

    @ApiModelProperty(value = "부적합 체크리스트")
    private final List<DiaryChecklistDto> badChecklist;

    @ApiModelProperty(value = "스티커 JSON")
    private final String sticker;

    public DiaryDto(Long id) {
        this.id = id;
        this.emoji = Emoji.ANGRY;
        this.emojiUrl = "https://i.ibb.co/zbHrDVm/angry.png";
        this.date = LocalDate.now();
        this.content = "다람쥐 헌 쳇바퀴에 타고파";
        this.tags = new ArrayList<>();
        tags.add("거짓말");
        tags.add("가스라이팅");
        this.goodChecklist = new ArrayList<>();
        goodChecklist.add(new DiaryChecklistDto("이야기를 잘 듣고 공감해주는 친구", true));
        goodChecklist.add(new DiaryChecklistDto("존중하고 배려하는 마음을 가진 친구 ", true));
        goodChecklist.add(new DiaryChecklistDto("관심사가 비슷한 친구", false));
        goodChecklist.add(new DiaryChecklistDto("신뢰할 수 있는 친구", false));
        goodChecklist.add(new DiaryChecklistDto("성격이 잘 맞는 친구", true));

        this.badChecklist = new ArrayList<>();
        badChecklist.add(new DiaryChecklistDto("나를 배려하지 않는 친구", false));
        badChecklist.add(new DiaryChecklistDto("신뢰를 잃는 행동을 하는 친구", true));
        badChecklist.add(new DiaryChecklistDto("나의 자존감을 낮추는 친구", false));
        badChecklist.add(new DiaryChecklistDto("유머코드가 맞지 않는 친구", true));
        badChecklist.add(new DiaryChecklistDto("나에게 너무 많이 의존하는 친구", false));

        this.sticker = "[{sticker: KK, url: \"https://i.ibb.co/zbHrDVm/angry.png\"}]";
    }


}

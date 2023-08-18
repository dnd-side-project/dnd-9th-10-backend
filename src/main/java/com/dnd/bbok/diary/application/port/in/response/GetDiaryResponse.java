package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.domain.Emoji;
import com.dnd.bbok.domain.diary.dto.response.DiaryChecklistDto;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final List<DiaryChecklistDto> goodChecklist;

    @ApiModelProperty(value = "부적합 체크리스트")
    private final List<DiaryChecklistDto> badChecklist;

    @ApiModelProperty(value = "스티커 JSON")
    private final String sticker;

    public GetDiaryResponse(Long id) {
        this.id = id;
        this.emoji = Emoji.ANGRY;
        this.emojiUrl = "https://i.ibb.co/zbHrDVm/angry.png";
        this.date = LocalDate.now();
        this.content = "다람쥐 헌 쳇바퀴에 타고파";
        this.tags = new ArrayList<>();
        tags.add("거짓말");
        tags.add("가스라이팅");
        this.goodChecklist = new ArrayList<>();
        goodChecklist.add(new DiaryChecklistDto(1L, "이야기를 잘 듣고 공감해주는 친구", true));
        goodChecklist.add(new DiaryChecklistDto(1L, "존중하고 배려하는 마음을 가진 친구 ", true));
        goodChecklist.add(new DiaryChecklistDto(1L, "관심사가 비슷한 친구", false));
        goodChecklist.add(new DiaryChecklistDto(1L, "신뢰할 수 있는 친구", false));
        goodChecklist.add(new DiaryChecklistDto(1L, "성격이 잘 맞는 친구", true));

        this.badChecklist = new ArrayList<>();
        badChecklist.add(new DiaryChecklistDto(1L, "나를 배려하지 않는 친구", false));
        badChecklist.add(new DiaryChecklistDto(1L, "신뢰를 잃는 행동을 하는 친구", true));
        badChecklist.add(new DiaryChecklistDto(1L, "나의 자존감을 낮추는 친구", false));
        badChecklist.add(new DiaryChecklistDto(1L, "유머코드가 맞지 않는 친구", true));
        badChecklist.add(new DiaryChecklistDto(1L, "나에게 너무 많이 의존하는 친구", false));

        this.sticker = "[{sticker: KK, url: \"https://i.ibb.co/zbHrDVm/angry.png\"}]";
    }

    public GetDiaryResponse(DiaryEntity diaryEntity, List<DiaryTagEntity> tags, List<DiaryChecklistEntity> checklist) {

        this.id = diaryEntity.getId();
        this.emoji = diaryEntity.getEmoji();
        this.date = diaryEntity.getDiaryDate();
        this.content = diaryEntity.getContents();
        // Diary Tag 중에서 현재 Diary 의 id 를 가지는 태그들을 찾고, 그 태그들의 이름을 Friend Tag 에서 가져옴
        this.tags = tags.stream().filter(tag -> Objects.equals(tag.getDiaryEntity().getId(), diaryEntity.getId())).map(tag -> tag.getFriendTagEntity().getName()).collect(Collectors.toList());

        this.goodChecklist = new ArrayList<>();
        this.badChecklist = new ArrayList<>();
        // Diary Checklist 중에서 현재 Diary 의 id를 가지는 체크리스트들을 찾고, 그 태그들로 각 checklist 생성
        List<DiaryChecklistEntity> targetChecklist = checklist.stream().filter(ele -> Objects.equals(ele.getDiaryEntity().getId(), diaryEntity.getId())).collect(Collectors.toList());
        targetChecklist.stream().filter(ele -> ele.getMemberChecklistEntity().isGood()).forEach(ele -> this.goodChecklist.add(new DiaryChecklistDto(ele.getId(), ele.getMemberChecklistEntity().getCriteria(), ele.isChecked())));
        targetChecklist.stream().filter(ele -> !ele.getMemberChecklistEntity().isGood()).forEach(ele -> this.badChecklist.add(new DiaryChecklistDto(ele.getId(), ele.getMemberChecklistEntity().getCriteria(), ele.isChecked())));

        // TODO 수정필요
        this.sticker = "[{sticker: KK, url: \"https://i.ibb.co/zbHrDVm/angry.png\"}]";
        this.emojiUrl = "https://i.ibb.co/zbHrDVm/angry.png";
    }
}

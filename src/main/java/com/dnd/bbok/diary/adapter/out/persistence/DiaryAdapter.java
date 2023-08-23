package com.dnd.bbok.diary.adapter.out.persistence;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.diary.adapter.out.persistence.mapper.DiaryChecklistMapper;
import com.dnd.bbok.diary.adapter.out.persistence.mapper.DiaryMapper;
import com.dnd.bbok.diary.adapter.out.persistence.mapper.DiaryTagMapper;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryRepository;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryTagRepository;
import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import com.dnd.bbok.diary.application.port.out.SaveDiaryPort;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryChecklistRepository;
import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTagRepository;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendRepository;
import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberChecklistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dnd.bbok.global.exception.ErrorCode.DIARY_NOT_FOUND;
import static com.dnd.bbok.global.exception.ErrorCode.FRIEND_NOT_FOUND;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiaryAdapter implements SaveDiaryPort, LoadDiaryPort {
    private final EntityManager entityManager;

    private final FriendTagRepository friendTagRepository;
    private final FriendRepository friendRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryTagRepository diaryTagRepository;
    private final DiaryChecklistRepository diaryChecklistRepository;
    private final MemberChecklistRepository memberChecklistRepository;

    private final DiaryTagMapper diaryTagMapper;
    private final DiaryChecklistMapper diaryChecklistMapper;
    private final DiaryMapper diaryMapper;

    @Override
    @Transactional
    public void createDiary(Long friendId, Diary diary) {
        FriendEntity friend = friendRepository.findById(friendId).orElseThrow(() -> new BusinessException(FRIEND_NOT_FOUND));

        // 1. 태그들 중 id 가 없는 태그가 있다면 Friend Tag Entity 생성
        if (diary.getTags() != null) {
            diary.getTags().forEach(tag -> {
                if (tag.getId() == null) {
                    FriendTagEntity friendTagEntity = friendTagRepository.save(FriendTagEntity.builder()
                            .friend(friend)
                            .name(tag.getTag())
                            .build());
                    tag.setId(friendTagEntity.getId());
                }
            });
        }

        // 2. 다이어리 생성
        DiaryEntity diaryEntity = diaryRepository.save(DiaryEntity.builder()
                .friend(friend)
                .contents(diary.getContents())
                .diaryDate(diary.getDiaryDate())
                .isDeleted(false)
                .emoji(diary.getEmoji())
                .sticker(diary.getSticker())
                .diaryScore(diary.getDiaryScore())
                .build());

        // 3. 다이어리 태그 생성
        if (diary.getTags().size() > 0) {
            List<FriendTagEntity> friendTagEntities = friendTagRepository.findAllByFriendId(friendId);
            List<DiaryTagEntity> diaryTagEntities = new ArrayList<>();
            diary.getTags().forEach(tag -> {
                FriendTagEntity friendTagEntity = friendTagEntities.stream().filter(ele -> Objects.equals(ele.getName(), tag.getTag())).findFirst().orElseThrow();
                diaryTagEntities.add(DiaryTagEntity.builder()
                        .diaryEntity(diaryEntity)
                        .friendTagEntity(friendTagEntity)
                        .build());
            });
            diaryTagRepository.saveAll(diaryTagEntities);
        }


        // 4. 다이어리 체크리스트 생성
        if (diary.getDiaryChecklist().size() > 0) {
            createDiaryChecklistEntity(diary, diaryEntity);
        }
    }

    @Override
    @Transactional
    public void saveDiary(Diary diary) {
        FriendEntity friendEntity = friendRepository.findById(diary.getFriendId()).orElseThrow(() -> new BusinessException(FRIEND_NOT_FOUND));
        DiaryEntity diaryEntity = diaryMapper.toEntity(diary, friendEntity);
        List<DiaryTagEntity> prevTagEntities = diaryTagRepository.findByDiaryId(diary.getId());
        List<Tag> prevTags = diaryTagMapper.toDomain(prevTagEntities);
        List<FriendTagEntity> friendTagEntities = null;
        for (Tag tag : diary.getTags()) {
            if (prevTags.stream().noneMatch(prevTag -> Objects.equals(tag.getTag(), prevTag.getTag()))) {
                // 없던 태그가 생겼으므로 새로 생성해야 함
                if (friendTagEntities == null) {
                    friendTagEntities = friendTagRepository.findAllByFriendId(friendEntity.getId());
                }

                // 아예 처음 생기는 태그인 경우 Friend Tag 생성
                FriendTagEntity friendTagEntity;
                if (tag.getId() == null) {
                    log.info("새로운 Friend Tag 저장");
                    friendTagEntity = friendTagRepository.save(FriendTagEntity.builder()
                            .friend(friendEntity)
                            .name(tag.getTag())
                            .build());
                    tag.setId(friendTagEntity.getId());
                    entityManager.persist(friendTagEntity);
                    friendTagEntities.add(friendTagEntity);
                    log.info("새로운 Friend Tag 저장 완료");
                }
            }
        }
        List<DiaryTagEntity> diaryTagEntities = diaryTagMapper.toEntity(diary.getTags(), diaryEntity, prevTagEntities, friendEntity, friendTagEntities);
        List<MemberChecklistEntity> memberChecklistEntities = memberChecklistRepository.findByIdIn(diary.getDiaryChecklist().stream().map(DiaryChecklist::getMemberChecklistId).collect(Collectors.toList()));
        log.info("체크리스트 갯수");
        log.info(diary.getDiaryChecklist().toString());
        List<DiaryChecklistEntity> diaryChecklistEntities = diaryChecklistMapper.toEntity(diary.getDiaryChecklist(), memberChecklistEntities, diaryEntity);
        diaryEntity.setDiaryTags(diaryTagEntities);
        diaryEntity.setDiaryChecklists(diaryChecklistEntities);
        diaryRepository.save(diaryEntity);
    }

    private void createDiaryChecklistEntity(Diary diary, DiaryEntity diaryEntity) {
        List<MemberChecklistEntity> memberChecklistEntities = memberChecklistRepository.findByIdIn(diary.getDiaryChecklist().stream().map(DiaryChecklist::getMemberChecklistId).collect(Collectors.toList()));
        List<DiaryChecklistEntity> diaryChecklistEntities = new ArrayList<>();
        diary.getDiaryChecklist().forEach(diaryChecklist -> {
            log.info(String.valueOf(diaryChecklist.getMemberChecklistId()));
            MemberChecklistEntity memberChecklistEntity = memberChecklistEntities.stream().filter(ele -> Objects.equals(ele.getId(), diaryChecklist.getMemberChecklistId())).findFirst().orElseThrow();
            diaryChecklistEntities.add(DiaryChecklistEntity.builder()
                    .diaryEntity(diaryEntity)
                    .memberChecklistEntity(memberChecklistEntity)
                    .isChecked(diaryChecklist.getIsChecked())
                    .build());
        });
        diaryChecklistRepository.saveAll(diaryChecklistEntities);
    }

    @Override
    public Diary loadDiary(Long diaryId) {
        List<Tag> tags = diaryTagMapper.toDomain(diaryTagRepository.findByDiaryId(diaryId));
        List<DiaryChecklist> diaryChecklists = diaryChecklistMapper.toDomain(diaryChecklistRepository.getDiaryChecklistByDiaryId(diaryId));
        return diaryMapper.toDomain(diaryRepository.findById(diaryId).orElseThrow(() -> new BusinessException(DIARY_NOT_FOUND)), tags, diaryChecklists);
    }

    @Override
    public Page<Diary> loadDiaries(Long friendId, Integer offset, String order, String keyword, String tag) {
        if (offset == null) {
            offset = 0;
        }

        Pageable pageable;
        int PAGE_SIZE = 20;
        if (Objects.equals(order, "asc")) {
            pageable = PageRequest.of(offset / PAGE_SIZE, PAGE_SIZE, Sort.Direction.ASC, "diaryDate");
        } else {
            pageable = PageRequest.of(offset / PAGE_SIZE, PAGE_SIZE, Sort.Direction.DESC, "diaryDate");
        }

        if (keyword == null) {
            keyword = "";
        }

        if (tag == null) {
            tag = "";
        }


        Page<DiaryEntity> diaryEntityPage = diaryRepository.findDiaries(friendId, keyword, tag, pageable);
        List<Long> diaryIds = diaryEntityPage.stream().map(DiaryEntity::getId).collect(Collectors.toList());
        List<Tag> tags = diaryTagMapper.toDomain(diaryTagRepository.findByDiaryIds(diaryIds));
        List<DiaryChecklist> checklists = diaryChecklistMapper.toDomain(diaryChecklistRepository.getDiaryChecklistByDiaryIds(diaryIds));

        Page<Diary> diaryPage = diaryEntityPage.map(diaryEntity -> diaryMapper.toDomain(diaryEntity, tags, checklists));
        return diaryPage;
    }

    /**
     * 친구 점수 전체 재계산을 위해 사용하는 메소드
     *
     * @param friendId 친구 아이디
     * @return 태그와 체크리스트가 비어있는 일기 도메인 리스트
     */
    @Override
    public List<Diary> loadDiariesByFriendId(Long friendId) {
        return diaryRepository.findAllByFriendId(friendId).stream().map(entity -> diaryMapper.toDomain(entity, new ArrayList<>(), new ArrayList<>())).collect(Collectors.toList());
    }

    @Override
    public int countDiariesByFriendId(Long friendId) {
        return this.diaryRepository.countDiariesByFriendId(friendId);
    }
}

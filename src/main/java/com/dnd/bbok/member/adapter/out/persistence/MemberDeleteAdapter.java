package com.dnd.bbok.member.adapter.out.persistence;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryChecklistRepository;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryRepository;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryTagRepository;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendRepository;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTagRepository;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberChecklistRepository;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberRepository;
import com.dnd.bbok.member.application.port.out.DeleteMemberPort;
import com.dnd.bbok.saying.adapter.out.persistence.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemberDeleteAdapter implements DeleteMemberPort {

    private final MemberRepository memberRepository;
    private final MemberChecklistRepository memberChecklistRepository;
    private final FriendTagRepository friendTagRepository;
    private final FriendRepository friendRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryTagRepository diaryTagRepository;
    private final DiaryChecklistRepository diaryChecklistRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public void deleteMember(UUID memberId) {
        // 지워야 할 거
        // 1. bookmark
        // 2. diary_tag
        // 3. diary_checklist
        // 4. diary
        // 5. friend_tag
        // 6. friend
        // 7. member_checklist
        // 8. member
        bookmarkRepository.deleteByMemberId(memberId);

        List<FriendEntity> friends = friendRepository.findAllFriends(memberId);
        for (FriendEntity friend : friends) {
            List<FriendTagEntity> friendTags = friendTagRepository.findAllByFriendId(friend.getId());
            List<DiaryEntity> diaries = diaryRepository.findAllByFriendId(friend.getId());
            List<DiaryTagEntity> diaryTags = diaryTagRepository.findByDiaryIds(diaries.stream().map(DiaryEntity::getId).collect(Collectors.toList()));
            List<DiaryChecklistEntity> diaryChecklist = diaryChecklistRepository.getDiaryChecklistByDiaryIds(diaries.stream().map(DiaryEntity::getId).collect(Collectors.toList()));
            diaryTagRepository.deleteAll(diaryTags);
            diaryChecklistRepository.deleteAll(diaryChecklist);
            diaryRepository.deleteAll(diaries);
            friendTagRepository.deleteAll(friendTags);
        }
        friendRepository.deleteAll(friends);
        List<MemberChecklistEntity> memberChecklist = memberChecklistRepository.findByMemberId(memberId);
        memberChecklistRepository.deleteAll(memberChecklist);

        memberRepository.deleteById(memberId);
    }
}

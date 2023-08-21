package com.dnd.bbok.saying.application.service;

import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.saying.application.port.in.request.CreateBookmarkRequest;
import com.dnd.bbok.saying.application.port.in.response.GetBookmarkGroupResponse;
import com.dnd.bbok.saying.application.port.in.response.GetSayingResponse;
import com.dnd.bbok.saying.application.port.in.usecase.GetBookmarksQuery;
import com.dnd.bbok.saying.application.port.in.usecase.SaveBookmarkUseCase;
import com.dnd.bbok.saying.application.port.out.BookmarkSayingPort;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import com.dnd.bbok.saying.application.port.out.LoadSayingPort;
import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SayingService implements SaveBookmarkUseCase, GetBookmarksQuery {

  private final LoadMemberPort loadMemberPort;
  private final BookmarkSayingPort bookmarkSayingPort;
  private final LoadSayingPort loadSayingPort;
  private final LoadBookmarkPort loadBookmarkPort;

  @Override
  public void createBookmark(UUID memberId, CreateBookmarkRequest bookmarkRequest) {
    Member member = loadMemberPort.loadById(memberId);
    Saying saying = loadSayingPort.getSaying(bookmarkRequest.getId());
    Bookmark bookmark = bookmarkSayingPort.checkBookmark(saying, member);

    //북마크 도메인을 북마크 레포지터리에 저장해놓는다.
    bookmarkSayingPort.saveBookmark(bookmark);
  }

  @Override
  public GetBookmarkGroupResponse getBookmarks(UUID memberId) {
    List<Bookmark> bookmarks = loadBookmarkPort.getBookmarks(memberId);
    List<Saying> memberSaying = loadSayingPort.getBookmarkSaying(bookmarks);

    List<GetSayingResponse> sayings = memberSaying.stream()
        .map(saying -> new GetSayingResponse(saying.getId(), saying.getContents(),
            saying.getReference()))
        .collect(Collectors.toList());

    return new GetBookmarkGroupResponse(sayings);
  }
}

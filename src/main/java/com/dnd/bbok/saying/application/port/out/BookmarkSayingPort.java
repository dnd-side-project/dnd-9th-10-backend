package com.dnd.bbok.saying.application.port.out;

import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;

public interface BookmarkSayingPort {

  Bookmark checkBookmark(Saying saying, Member member);

  void saveBookmark(Bookmark bookmark);

}

package com.dnd.bbok.friend.application.port.in.usecase;


import com.dnd.bbok.friend.application.port.in.response.GetBbokCharacterGroupResponse;

/**
 * 아이콘을 조회하는 메서드
 */
public interface GetIconQuery {

  GetBbokCharacterGroupResponse getCharacterIconUrl();

}

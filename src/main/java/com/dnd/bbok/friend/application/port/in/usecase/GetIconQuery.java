package com.dnd.bbok.friend.application.port.in.usecase;


import com.dnd.bbok.friend.application.port.in.response.BbokCharactersDto;

/**
 * 아이콘을 조회하는 메서드
 */
public interface GetIconQuery {

  BbokCharactersDto getCharacterIconUrl();

}

package com.dnd.bbok.friend.application.service;

import com.dnd.bbok.domain.friend.dto.response.BbokCharactersDto;
import com.dnd.bbok.friend.application.port.in.usecase.GetIconQuery;
import com.dnd.bbok.friend.application.port.out.LoadIconPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendIconService implements GetIconQuery {

  private final LoadIconPort loadIconPort;

  @Override
  public BbokCharactersDto getCharacterIcon() {
    return null;
  }
}

package com.dnd.bbok.friend.application.service;

import com.dnd.bbok.friend.application.port.in.response.BbokCharacterDto;
import com.dnd.bbok.friend.application.port.in.response.BbokCharactersDto;
import com.dnd.bbok.friend.application.port.in.usecase.GetIconQuery;

import com.dnd.bbok.friend.application.port.out.LoadIconPort;
import com.dnd.bbok.friend.domain.BbokCharacter;
import com.dnd.bbok.infra.s3.service.S3Downloader;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendIconService implements GetIconQuery {

  private final LoadIconPort loadIconPort;
  private final S3Downloader s3Downloader;

  @Override
  public BbokCharactersDto getCharacterIconUrl() {
    List<BbokCharacter> allCharacter = loadIconPort.getAllCharacter();
    List<BbokCharacterDto> list = new ArrayList<>();
    for(BbokCharacter character : allCharacter) {
      list.add(
          new BbokCharacterDto(character, s3Downloader.getIconUrl(character.getIconFile()))
      );
    }
    return new BbokCharactersDto(list);
  }



}

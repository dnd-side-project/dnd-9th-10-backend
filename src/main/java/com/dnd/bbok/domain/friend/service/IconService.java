package com.dnd.bbok.domain.friend.service;

import com.dnd.bbok.domain.friend.dto.response.BbokCharacterDto;
import com.dnd.bbok.domain.friend.dto.response.BbokCharactersDto;
import com.dnd.bbok.domain.friend.entity.BbokCharacter;
import com.dnd.bbok.infra.s3.service.S3Downloader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * S3에 저장된 아이콘을 불러오는 서비스.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IconService {

  private final S3Downloader s3Downloader;

  public BbokCharactersDto getCharacterIcon() {
    List<BbokCharacterDto> list = new ArrayList<>();
    //1. 모든 캐릭터를 가져온다.
    List<BbokCharacter> characters = getBbokCharactersWithPrefixCond("front_");
    //2. 캐릭터를 dto에 매핑한다.
    for(BbokCharacter character : characters) {
      BbokCharacterDto bbokCharacterDto = new BbokCharacterDto(character,
          s3Downloader.getIconUrl(character.getIconFile()));
      list.add(bbokCharacterDto);
    }
    return new BbokCharactersDto(list);
  }

  /**
   * 캐릭터가 정면(front)을 보는 경우도 있고, 측면(side)를 보는 경우를 위해서, prefix에 따라 아이콘을 가져오는 메서드.
   * @param prefix "front_" or "side_"
   * @return List<BbokcCharacter>
   */
  private List<BbokCharacter> getBbokCharactersWithPrefixCond(String prefix) {
    return Arrays.stream(BbokCharacter.values())
        .filter(character -> character.getIconFile().startsWith(prefix))
        .collect(Collectors.toList());
  }
}

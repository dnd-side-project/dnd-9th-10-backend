package com.dnd.bbok.friend.adapter.out.persistence;

import com.dnd.bbok.friend.application.port.out.LoadIconPort;
import com.dnd.bbok.friend.domain.BbokCharacter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendCharacterAdapter implements LoadIconPort {

  @Override
  public List<BbokCharacter> getAllCharacter() {
    return Arrays.stream(BbokCharacter.values())
        .filter(character -> character.getIconFile().startsWith("front_"))
        .collect(Collectors.toList());
  }

  @Override
  public BbokCharacter getCharacter() {
    return null;
  }

}

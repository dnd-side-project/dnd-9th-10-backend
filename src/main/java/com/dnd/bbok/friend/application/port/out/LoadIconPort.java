package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.domain.BbokCharacter;
import java.util.List;

public interface LoadIconPort {

  List<BbokCharacter> getAllCharacter();

  BbokCharacter getCharacter(String bbok);

}

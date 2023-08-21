package com.dnd.bbok.saying.application.port.out;

import java.util.UUID;

public interface DeleteBookmarkPort {

  void delete(UUID memberId, Long sayingId);

}

package com.dnd.bbok.saying.application.port.out;

import java.util.UUID;

public interface LoadBookmarkPort {
    boolean isMarked(UUID memberId, Long sayingId);
}

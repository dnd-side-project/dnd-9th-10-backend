package com.dnd.bbok.saying.application.port.out;

import com.dnd.bbok.saying.domain.Saying;

import java.util.List;

public interface LoadSayingPort {
    List<Saying> getAllSaying();
}

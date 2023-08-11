package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.domain.checklist.dto.response.BasicChecklistDto;
import com.dnd.bbok.domain.checklist.entity.BasicChecklist;
import com.dnd.bbok.domain.checklist.repository.BasicChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicChecklistService {
    private final BasicChecklistRepository basicChecklistRepository;

    public List<BasicChecklist> getBasicChecklist() {
        return basicChecklistRepository.findAll();
    }
    
    public <T> T createWithEntity(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = tClass.getDeclaredConstructor(List.class);
        return constructor.newInstance(basicChecklistRepository.findAll());
    }
}


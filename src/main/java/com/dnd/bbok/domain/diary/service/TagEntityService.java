package com.dnd.bbok.domain.diary.service;


import com.dnd.bbok.domain.diary.repository.TagRepository;
import com.dnd.bbok.domain.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagEntityService {
    private final TagRepository tagRepository;

    public void createTag(String name) {
        // Tag tag = Tag.builder();
    }
}

package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.repositories.TagRepository;
import com.petushkov.webappcollections.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Override
    public Set<String> getTagNames() {

        Set<String> tags = new HashSet<>();
        tagRepository.findAll().forEach(t-> tags.add(t.getName()));
        return tags;

    }
}

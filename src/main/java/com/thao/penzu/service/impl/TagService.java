package com.thao.penzu.service.impl;

import com.thao.penzu.model.Tag;
import com.thao.penzu.repository.ITagRepository;
import com.thao.penzu.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Iterable<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Iterable<Tag> findTagsByNameContaining(String tag_name) {
        return tagRepository.findTagsByNameContaining(tag_name);
    }
}

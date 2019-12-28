package com.thao.penzu.service;

import com.thao.penzu.model.Tag;

import java.util.Optional;

public interface ITagService {
    Optional<Tag> findById(Long id);

    Iterable<Tag> findAll();

    void delete(Long id);

    Tag save(Tag tag);

    Iterable<Tag> findTagsByNameContaining(String tag_name);



}

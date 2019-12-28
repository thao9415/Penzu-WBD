package com.thao.penzu.repository;

import com.thao.penzu.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {
    Iterable<Tag> findTagsByNameContaining(String tag_name);
}

package com.thao.penzu.repository;

import com.thao.penzu.model.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiaryRepository extends JpaRepository<Diary, Long> {
    Iterable<Diary> findDiariesByUserId(Long user_id);

    Iterable<Diary> findDiariesByTagId(Long tag_id);

    Iterable<Diary> findDiariesByTitleContaining(String title);

    Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id);

    Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title);

    Page<Diary> findAllByOrderByDateAsc(Pageable pageable);

    Page<Diary> findAllByOrderByDateDesc(Pageable pageable);

}

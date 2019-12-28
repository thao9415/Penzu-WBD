package com.thao.penzu.service.impl;

import com.thao.penzu.model.Diary;
import com.thao.penzu.repository.IDiaryRepository;
import com.thao.penzu.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryService implements IDiaryService {
    @Autowired
    IDiaryRepository diaryRepository;

    @Override
    public Optional<Diary> findById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public Iterable<Diary> findAll() {
        return diaryRepository.findAll();
    }

    @Override
    public Page<Diary> findAll(Pageable pageable) {
        return diaryRepository.findAll(pageable);
    }

    @Override
    public Diary save(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public void delete(Long id) {
        diaryRepository.deleteById(id);

    }

    @Override
    public Iterable<Diary> findDiariesByUserId(Long user_id) {
        return diaryRepository.findDiariesByUserId(user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTagId(Long tag_id) {
        return diaryRepository.findDiariesByTagId(tag_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContaining(String title) {
        return diaryRepository.findDiariesByTitleContaining(title);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id) {
        return diaryRepository.findDiariesByTitleContainingAndUserId(title, user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title) {
        return diaryRepository.findDiariesByTagIdAndTitleContaining(tag_id, title);
    }

    @Override
    public Page<Diary> findAllByOrderByDateAsc(Pageable pageable) {
        return diaryRepository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Diary> findAllByOrderByDateDesc(Pageable pageable) {
        return diaryRepository.findAllByOrderByDateDesc(pageable);
    }
}

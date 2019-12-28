package com.thao.penzu.controller;

import com.thao.penzu.message.request.SearchDiaryByTagAndTitle;
import com.thao.penzu.message.request.SearchDiaryByTitle;
import com.thao.penzu.message.request.SearchDiaryByTitleAndUserId;
import com.thao.penzu.model.Diary;
import com.thao.penzu.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class DiaryRestAPI {

    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/diary")
    public ResponseEntity<?> getListDiary() {
        List<Diary> diaries = (List<Diary>) diaryService.findAll();
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @GetMapping("/diary/{id}")
    public ResponseEntity<?> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.findById(id);
        if (diary.isPresent()) {
            return new ResponseEntity<>(diary, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/diary")
    public ResponseEntity<?> createDiary(@Valid @RequestBody Diary diary) {
        LocalDateTime now = LocalDateTime.now();
        diary.setDate(now);
        diaryService.save(diary);
        return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

    @DeleteMapping("/diary/{id}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.findById(id);
        if (diary.isPresent()) {
            diaryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/diary/{id}")
    public ResponseEntity<?> updateDiary(@RequestBody Diary diary, @PathVariable Long id) {
        //diary1 kiểu optional chứ k phải kiểu Diary nên phải diary1.get() để lấy về diary
        Optional<Diary> diary1 = diaryService.findById(id);
        if (!diary1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        diary1.get().setDate(diary.getDate());
        diary1.get().setContent(diary.getContent());
        diary1.get().setTitle(diary.getTitle());
        diary1.get().setTag(diary.getTag());
        diary1.get().setDescription(diary.getDescription());
        diary1.get().setUser(diary.getUser());

        diaryService.save(diary1.get());

        return new ResponseEntity<>(diary1, HttpStatus.OK);
    }

    @PostMapping("/diary/search-by-title-user-id")
    public ResponseEntity<?> searchDiaryByTitleAndUserId(@RequestBody SearchDiaryByTitleAndUserId formSearch) {
        List<Diary> diaries;
        if (formSearch.getTitle() == "") {
            diaries = (List<Diary>) diaryService.findAll();
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        diaries = (List<Diary>) diaryService.findDiariesByTitleContainingAndUserId(formSearch.getTitle(), formSearch.getId());
        return new ResponseEntity<>(diaries, HttpStatus.OK);


    }

    @PostMapping("/diary/search-by-tag-id/{id}")
    public ResponseEntity<?> searchDiaryByTagId(@PathVariable Long id) {
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(id);
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @PostMapping("/diary/search-by-title")
    public ResponseEntity<?> searchDiaryByTitle(@RequestBody SearchDiaryByTitle searchForm) {
        List<Diary> diaries;
        if (searchForm.getTitle() == "") {
            diaries = (List<Diary>) diaryService.findAll();
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }
        diaries = (List<Diary>) diaryService.findDiariesByTitleContaining(searchForm.getTitle());
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

//    @PostMapping("/diary/search-by-tag-and-title")
//    public ResponseEntity<?> searchDiaryByTagAndTitle(@RequestBody SearchDiaryByTagAndTitle searchForm) {
//
//        if (searchForm.getTag() == null && searchForm.getTitle() == null) {
//            List<Diary> diaries = (List<Diary>) diaryService.findAll();
//            if (diaries.isEmpty()) {
//                return new ResponseEntity(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(diaries, HttpStatus.OK);
//        }
//
//        if (searchForm.getTag() == null && searchForm.getTitle() != null) {
//            List<Diary> diaries = (List<Diary>) diaryService.findDi
//
//        }
//
//    }

}

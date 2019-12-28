package com.thao.penzu.controller;

import com.thao.penzu.message.request.SearchTagByName;
import com.thao.penzu.model.Diary;
import com.thao.penzu.model.Tag;
import com.thao.penzu.repository.ITagRepository;
import com.thao.penzu.service.IDiaryService;
import com.thao.penzu.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class TagRestAPI {
    @Autowired
    private ITagService tagService;

    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/tag")
    public ResponseEntity<?> findListAllTags() {
        List<Tag> tags = (List<Tag>) tagService.findAll();
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<?> findTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        if (tag.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("/tag")
    public ResponseEntity<?> createTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        if (!tag.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(id);
        for (Diary diary : diaries) {
//            Diary diary1 = new Diary();
//            diary1.setDate(diary.getDate());
//            diary1.setTitle(diary.getTitle());
//            diary1.setDescription(diary.getDescription());
//            diary1.setContent(diary.getContent());
//            diary1.setUser(diary.getUser());
//            diary.setTag();
//            diaryService.save(diary1);
            diaryService.delete(diary.getId());
        }
        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/tag/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Optional<Tag> tag1 = tagService.findById(id);
        if (!tag1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tag1.get().setName(tag.getName());
        tagService.save(tag1.get());
        return new ResponseEntity<>(tag1, HttpStatus.OK);
    }

    @PutMapping("/tag/search-by-name")
    public ResponseEntity<?> searchTagsByNameTag(@RequestBody SearchTagByName tagForm) {
        if (tagForm.getName() == "" || tagForm.getName() == null) {
            List<Tag> tags = (List<Tag>) tagService.findAll();
            if (tags.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }

        List<Tag> tags = (List<Tag>) tagService.findTagsByNameContaining(tagForm.getName());
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);

    }
}

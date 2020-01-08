package com.thao.penzu.controller;

import com.thao.penzu.model.Album;
import com.thao.penzu.model.Comment;
import com.thao.penzu.model.Diary;
import com.thao.penzu.service.IAlbumService;
import com.thao.penzu.service.ICommentService;
import com.thao.penzu.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class CommentRestAPI {
    @Autowired
    ICommentService commentService;
    @Autowired
    IDiaryService diaryService;
    @Autowired
    IAlbumService albumService;

    @GetMapping("/comment")
    public ResponseEntity<?> getAllComment() {
        List<Comment> comments = (List<Comment>) commentService.findAll();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/comment/diary/{id}")
    public ResponseEntity<?> getCommentsByDiaryId(@PathVariable Long id) {
       Optional<Diary> diary =  diaryService.findById(id);
       if(!diary.isPresent()) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        List<Comment> comments = (List<Comment>) commentService.findCommentsByDiaryId(id);
        if(comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/album/{id}")
    public ResponseEntity<?> getCommentsByAlbumId(@PathVariable Long id) {
        Optional<Album> album =  albumService.findById(id);
        if(!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comment> comments = (List<Comment>) commentService.findCommentsByAlbumId(id);
        if(comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (!comment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        //từ comment có id diary => tìm diary đó.
        //set diary cho comment
        if(comment.getIdDiary() != null) {
            Optional<Diary> diary = diaryService.findById(comment.getIdDiary());
            comment.setDiary(diary.get());
        }
        if (comment.getIdAlbum()!= null) {
            Optional<Album> album = albumService.findById(comment.getIdAlbum());
            comment.setAlbum(album.get());
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);
        comment.setEdit(false);
        comment.setDate(date);

        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);
        if (!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);
        comment1.get().setEdit(true);
        comment1.get().setDate(date);
        comment1.get().setContent(comment.getContent());

        return new ResponseEntity<>(comment1, HttpStatus.OK);
    }
}

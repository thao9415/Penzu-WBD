package com.thao.penzu.service;

import com.thao.penzu.model.Comment;

import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();
    Optional<Comment> findById(Long id);
    void delete(Long id);
    Comment save(Comment comment);
    Iterable<Comment> findCommentsByAlbumId(Long album_id);
    Iterable<Comment> findCommentsByDiaryId(Long diary_id);
}

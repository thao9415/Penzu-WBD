package com.thao.penzu.service.impl;

import com.thao.penzu.model.Comment;
import com.thao.penzu.repository.ICommentRepository;
import com.thao.penzu.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository iCommentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        iCommentRepository.deleteById(id);

    }

    @Override
    public Comment save(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> findCommentsByAlbumId(Long album_id) {
        return iCommentRepository.findCommentsByAlbumId(album_id);
    }

    @Override
    public Iterable<Comment> findCommentsByDiaryId(Long diary_id) {
        return iCommentRepository.findCommentsByDiaryId(diary_id);
    }
}

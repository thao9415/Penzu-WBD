package com.thao.penzu.repository;

import com.thao.penzu.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findCommentsByAlbumId(Long album_id);
    Iterable<Comment> findCommentsByDiaryId(Long diary_id);

}

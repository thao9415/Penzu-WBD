package com.thao.penzu.service;

import com.thao.penzu.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAlbumService {
    Optional<Album> findById(Long id);
    Iterable<Album> findAll();
    void delete(Long id);
    Album save(Album album);

    Iterable<Album> findAllByUserId(Long id);

    Iterable<Album> findAlbumsByTitleContaining(String title);

    Page<Album> findAllByOrderByDateAsc(Pageable pageable);

    Page<Album> findAllByOrderByDateDesc(Pageable pageable);

    Iterable<Album> findAlbumsByTagId(Long tag_id);

    Iterable<Album> findAlbumsByTagIdAndTitleContaining(Long tag_id, String title);

}

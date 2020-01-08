package com.thao.penzu.service.impl;

import com.thao.penzu.model.Album;
import com.thao.penzu.repository.IAlbumRepository;
import com.thao.penzu.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    IAlbumRepository albumRepository;

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);

    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Iterable<Album> findAllByUserId(Long id) {
        return albumRepository.findAllByUserId(id);
    }

    @Override
    public Iterable<Album> findAlbumsByTitleContaining(String title) {
        return albumRepository.findAlbumsByTitleContaining(title);
    }

    @Override
    public Page<Album> findAllByOrderByDateAsc(Pageable pageable) {
        return albumRepository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Album> findAllByOrderByDateDesc(Pageable pageable) {
        return albumRepository.findAllByOrderByDateDesc(pageable);
    }

    @Override
    public Iterable<Album> findAlbumsByTagId(Long tag_id) {
        return albumRepository.findAlbumsByTagId(tag_id);
    }

    @Override
    public Iterable<Album> findAlbumsByTagIdAndTitleContaining(Long tag_id, String title) {
        return albumRepository.findAlbumsByTagIdAndTitleContaining(tag_id, title);
    }
}

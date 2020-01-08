package com.thao.penzu.service;

import com.thao.penzu.model.Image;

import java.util.Optional;

public interface IImageService {
    Iterable<Image> findAll();
    Optional<Image> findById(Long id);
    void delete(Long id);
    Image save(Image image);
    Iterable<Image> findImagesByAlbumId(Long album_id);
}

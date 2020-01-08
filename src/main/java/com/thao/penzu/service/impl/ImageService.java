package com.thao.penzu.service.impl;

import com.thao.penzu.model.Image;
import com.thao.penzu.repository.IImageRepository;
import com.thao.penzu.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    IImageRepository iImageRepository;

    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        iImageRepository.deleteById(id);

    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public Iterable<Image> findImagesByAlbumId(Long album_id) {
        return iImageRepository.findImagesByAlbumId(album_id);
    }
}

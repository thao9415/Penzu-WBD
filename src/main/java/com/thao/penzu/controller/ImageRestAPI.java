package com.thao.penzu.controller;

import com.thao.penzu.model.Album;
import com.thao.penzu.model.Image;
import com.thao.penzu.service.IAlbumService;
import com.thao.penzu.service.IImageService;
import com.thao.penzu.service.impl.ImageFirebaseServiceExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class ImageRestAPI {
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

    @GetMapping("/image/search-image-by-albumId/{id}")
    public ResponseEntity<?> searchImagesByAlbumId(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);
        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Image> images = (List<Image>) imageService.findImagesByAlbumId(id);
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);

    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteImageById(@PathVariable Long id) {
        Optional<Image> image = imageService.findById(id);
        if (!image.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(image.get().getBlobString() != null) {
            imageFirebaseServiceExtends.deleteFirebaseStorageFile(image.get());
        }
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

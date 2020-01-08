package com.thao.penzu.controller;

import com.thao.penzu.message.request.SearchAlbumByTagIdAndTitle;
import com.thao.penzu.message.request.SearchAlbumByTitle;
import com.thao.penzu.model.Album;
import com.thao.penzu.model.Image;
import com.thao.penzu.service.IAlbumService;
import com.thao.penzu.service.IImageService;
import com.thao.penzu.service.impl.AlbumFirebaseServiceExtends;
import com.thao.penzu.service.impl.ImageFirebaseServiceExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AlbumRestAPI {
    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IImageService iImageService;

    @Autowired
    private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

    @Autowired
    private AlbumFirebaseServiceExtends albumFirebaseServiceExtends;

    @GetMapping("/album")
    public ResponseEntity<?> getAllAlbum() {
        List<Album> albumList = (List<Album>) albumService.findAll();
        if (albumList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);
        if (album.isPresent()) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/album")
    public ResponseEntity<?> createAlbum(@RequestBody Album album) {
        LocalDateTime now = LocalDateTime.now();
        album.setDate(now);
        albumService.save(album);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<?> updateAlbum(@RequestBody Album album, @PathVariable Long id) {
        Optional<Album> album1 = albumService.findById(id);
        if (!album1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        album1.get().setTag(album.getTag());
        album1.get().setDescription(album.getDescription());
        albumService.save(album1.get());
        return new ResponseEntity<>(album1, HttpStatus.OK);
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        Optional<Album> album = albumService.findById(id);

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Image> images = (List<Image>) iImageService.findImagesByAlbumId(id);
        if (images.isEmpty()) {
            for (int i = 0; i < images.size(); i++) {
                imageFirebaseServiceExtends.deleteFirebaseStorageFile(images.get(i));
            }

        }
        if (album.get().getBlobString() != null) {
            albumFirebaseServiceExtends.deleteFirebaseStorageFile(album.get());
        }
        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/album/search-by-userId/{id}")
    public ResponseEntity<?> getAlbumByUserId(@PathVariable Long id) {
        //thiếu kiểm tra xem user Id có tồn tại user tương ứng hay ko?
        List<Album> albums = (List<Album>) albumService.findAllByUserId(id);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("album/search-album-by-title")
    public ResponseEntity<?> getAlbumByTitle(@RequestBody SearchAlbumByTitle searchAlbumByTitle) {
        if (searchAlbumByTitle.getTitle().equals("") || searchAlbumByTitle.getTitle() == null) {
            List<Album> albums = (List<Album>) albumService.findAll();
            if (albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums, HttpStatus.OK);
        }
        List<Album> albums = (List<Album>) albumService.findAlbumsByTitleContaining(searchAlbumByTitle.getTitle());
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/album/pagination/ASC")
    public ResponseEntity<?> getAlbumsAndSortingByDateAsc(@PageableDefault(value = 100000) Pageable pageable) {
        Page<Album> albums = albumService.findAllByOrderByDateAsc(pageable);

        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }


    @GetMapping("/album/pagination/DSC")
    public ResponseEntity<?> getAlbumsAndSortingByDateDSC(@PageableDefault(value = 100000) Pageable pageable) {
        Page<Album> albums = albumService.findAllByOrderByDateDesc(pageable);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("/album/search-by-tagId-and-title")
    public ResponseEntity<?> searchAlbumByTagIdAndTitle(@RequestBody SearchAlbumByTagIdAndTitle formSearch) {
        if (formSearch.getTitle() == null && formSearch.getTag_id() == null) {
            List<Album> albums = (List<Album>) albumService.findAll();
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        }

        if (formSearch.getTitle() == null && formSearch.getTag_id() != null) {
            List<Album> albums = (List<Album>) albumService.findAlbumsByTagId(formSearch.getTag_id());
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        }

        if (formSearch.getTitle() != null && formSearch.getTag_id() == null) {
            List<Album> diaries = (List<Album>) albumService.findAlbumsByTitleContaining(formSearch.getTitle());
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }

        if (formSearch.getTag_id() != null && formSearch.getTitle() != null) {
            List<Album> albums = (List<Album>) albumService.findAlbumsByTagIdAndTitleContaining(formSearch.getTag_id(),formSearch.getTitle());
            if(albums.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albums,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

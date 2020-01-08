package com.thao.penzu.repository;

import com.thao.penzu.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Iterable<Image> findImagesByAlbumId(Long album_id);
}

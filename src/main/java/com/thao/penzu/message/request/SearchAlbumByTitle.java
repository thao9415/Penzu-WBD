package com.thao.penzu.message.request;

import org.springframework.web.multipart.MultipartFile;

public class SearchAlbumByTitle {
    private String title;

    public SearchAlbumByTitle() {
    }

    public SearchAlbumByTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

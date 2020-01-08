package com.thao.penzu.message.request;

public class SearchAlbumByTagIdAndTitle {
    private String title;
    private Long tag_id;

    public SearchAlbumByTagIdAndTitle(String title, Long tag_id) {
        this.title = title;
        this.tag_id = tag_id;
    }

    public SearchAlbumByTagIdAndTitle() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTag_id() {
        return tag_id;
    }

    public void setTag_id(Long tag_id) {
        this.tag_id = tag_id;
    }
}

package com.thao.penzu.message.request;

public class SearchDiaryByTitleAndUserId {
    private String title;
    private Long id;

    public SearchDiaryByTitleAndUserId(String title, Long id) {
        this.title = title;
        this.id = id;
    }

    public SearchDiaryByTitleAndUserId() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

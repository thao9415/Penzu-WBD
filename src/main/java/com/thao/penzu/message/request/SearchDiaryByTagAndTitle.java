package com.thao.penzu.message.request;

public class SearchDiaryByTagAndTitle {
    private String tag;
    private String title;

    public SearchDiaryByTagAndTitle() {
    }

    public SearchDiaryByTagAndTitle(String tag, String title) {
        this.tag = tag;
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

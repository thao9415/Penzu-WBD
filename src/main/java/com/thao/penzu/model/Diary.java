package com.thao.penzu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @Lob
    private String title;

    @Lob
    private String description;

    @Lob
    private String urlFile;

    @Lob
    private String blobString;
    private Boolean isUpdate;


    @Lob
    private String content;


    @ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class, mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Diary() {
    }


    public Diary(LocalDateTime date, String title, String description, String urlFile, String content) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.urlFile = urlFile;
        this.content = content;
    }

    public Diary(LocalDateTime date, String title, String description, String urlFile, Boolean isUpdate, String content, Tag tag, User user, List<Comment> comments) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.urlFile = urlFile;
        this.isUpdate = isUpdate;
        this.content = content;
        this.tag = tag;
        this.user = user;
        this.comments = comments;
    }


    public Diary(LocalDateTime date, String title, String description, String urlFile, Boolean isUpdate, String content, Tag tag, User user) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.urlFile = urlFile;
        this.isUpdate = isUpdate;
        this.content = content;
        this.tag = tag;
        this.user = user;
    }

    public Diary(LocalDateTime date, String title, String description, String urlFile, String content, Tag tag, User user) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.urlFile = urlFile;
        this.content = content;
        this.tag = tag;
        this.user = user;
    }

    public Diary(LocalDateTime date, String title, String description, String urlFile, String content, Tag tag) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.urlFile = urlFile;
        this.content = content;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = date.format(formatter);
        return now;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getBlobString() {
        return blobString;
    }

    public void setBlobString(String blobString) {
        this.blobString = blobString;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

package com.thao.penzu.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;

    public Diary() {
    }

    public Diary(LocalDateTime date, String title, String description, String content, User user, Tag tag) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.content = content;
        this.user = user;
        this.tag = tag;
    }
    public Diary(LocalDateTime date, String title, String description, String content, User user) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.content = content;
        this.user = user;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
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
}

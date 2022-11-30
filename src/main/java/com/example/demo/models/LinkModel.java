package com.example.demo.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LinkModel {
    private final Integer authorId;
    private String linkName;
    private String linkURL;
    private String description;
    private String tag;
    private String lastEditingDate;

    public LinkModel() {
        this("name");
    }

    public LinkModel(String linkName) {
        this(linkName, "url");
    }

    public LinkModel(String linkName, String linkURL) {
        this(linkName, linkURL, "no description");
    }

    public LinkModel(String linkName, String linkURL, String description) {
        this(1, linkName, linkURL, description);
    }

    public LinkModel(Integer authorId, String linkName, String linkURL, String description) {
        this.authorId = authorId;
        this.linkURL = linkURL;
        this.linkName = linkName;
        this.description = description;
    }

    public LinkModel(Integer authorId, String linkName, String linkURL, String description, String tag) {
        this.authorId = authorId;
        this.linkURL = linkURL;
        this.linkName = linkName;
        this.description = description;
        this.tag = tag;
        this.lastEditingDate = LocalDate.now().toString();
    }

    public LinkModel(Integer authorId, String linkName, String linkURL, String description, String tag, LocalDate date) {
        this.authorId = authorId;
        this.linkURL = linkURL;
        this.linkName = linkName;
        this.description = description;
        this.tag = tag;
        this.lastEditingDate = date.toString();
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastEditingDate = LocalDate.now().toString();
    }
}

package com.example.demo.models;

import lombok.Data;

@Data
public class LinkModel {
    private static final String DEFAULT_LINKNAME = "name";
    private static final String DEFAULT_LINKURL = "url";
    private static final String DEFAULT_DESCRIPTION = "no description";
    private final Integer authorId;
    private String linkName;
    private String linkURL;
    private String description;

    public LinkModel() {
        this(1);
    }

    public LinkModel(Integer authorId) {
        this(authorId, DEFAULT_LINKNAME);
    }

    public LinkModel(Integer authorId, String linkName) {
        this(authorId, linkName, DEFAULT_LINKURL);
    }

    public LinkModel(Integer authorId, String linkName, String linkURL) {
        this(authorId, linkName, linkURL, DEFAULT_DESCRIPTION);
    }

    public LinkModel(Integer authorId, String linkName, String linkURL, String description) {
        this.authorId = authorId;
        this.linkURL = linkURL;
        this.linkName = linkName;
        this.description = description;
    }
}

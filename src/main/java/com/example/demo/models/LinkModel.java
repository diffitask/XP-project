package com.example.demo.models;
public class LinkModel {
    private final Integer authorId;
    private String linkName;
    private String linkURL;
    private String description;

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

    public String getLinkName() {
        return linkName;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}

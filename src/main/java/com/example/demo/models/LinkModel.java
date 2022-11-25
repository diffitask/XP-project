package com.example.demo.models;

public class LinkModel {
    private final Integer authorId = 1;
    private String linkName = "google";
    private String linkURL = "google.com";
    private String description = "no description";

    public LinkModel(String linkName) {
        this.linkName = linkName;
    }

    public LinkModel(String linkName, String linkURL) {
        this(linkName);
        this.linkURL = linkURL;
    }

    public LinkModel(String linkName, String linkURL, String description) {
        this(linkName, linkURL);
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

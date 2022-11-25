package com.example.demo.models;

import java.util.List;

public interface LinkServiceInterface {
    List<LinkModel> getAllUserLinks(Integer userId);
    void saveLink(LinkModel linkModel);
}

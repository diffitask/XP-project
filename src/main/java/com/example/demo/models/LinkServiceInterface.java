package com.example.demo.models;

import com.example.demo.exceptions.LinkServiceException;

import java.util.List;

public interface LinkServiceInterface {
    List<LinkModel> getAllUserLinks(Integer userId) throws LinkServiceException;
    void saveLink(Integer userId, LinkModel linkModel) throws LinkServiceException;
}

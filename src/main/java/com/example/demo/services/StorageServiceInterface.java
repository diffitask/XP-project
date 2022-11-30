package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;

import java.util.List;

public interface StorageServiceInterface {
    List<LinkModel> getAllUserLinks(Integer userId) throws LinkServiceException;

    void saveLink(LinkModel linkModel) throws LinkServiceException;
}

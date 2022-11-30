package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;

import java.util.List;

public interface SortingServiceInterface {
    List<LinkModel> sortLinksByName(StorageService storageService, Integer userId) throws LinkServiceException;

    List<LinkModel> sortLinksByDate(StorageService storageService, Integer userId) throws LinkServiceException;
}

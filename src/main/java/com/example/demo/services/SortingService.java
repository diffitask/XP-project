package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;

import java.util.Comparator;
import java.util.List;

public class SortingService implements SortingServiceInterface {
    public SortingService() {
    }

    public List<LinkModel> sortLinksByName(StorageService storageService, Integer userId) throws LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        allLinks.sort(Comparator.comparing(LinkModel::getLinkName));
        return allLinks;
    }

    public List<LinkModel> sortLinksByDate(StorageService storageService, Integer userId) throws LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        allLinks.sort(Comparator.comparing(LinkModel::getLastEditingDate));
        return allLinks;
    }
}

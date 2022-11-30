package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;

import java.util.Comparator;
import java.util.List;

public class SortingService implements SortingServiceInterface {
    private final StorageServiceInterface storageService;

    public SortingService(StorageServiceInterface storageService) {
        this.storageService = storageService;
    }

    @Override
    public List<LinkModel> sortLinksByName(Integer userId) throws LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        allLinks.sort(Comparator.comparing(LinkModel::getLinkName));
        return allLinks;
    }

    @Override
    public List<LinkModel> sortLinksByTag(Integer userId) throws LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        allLinks.sort(Comparator.comparing(LinkModel::getTag));
        return allLinks;
    }

    @Override
    public List<LinkModel> sortLinksByDate(Integer userId) throws LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        allLinks.sort(Comparator.comparing(LinkModel::getCreationDate));
        return allLinks;
    }
}

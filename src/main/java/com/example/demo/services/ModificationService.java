package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.models.MapSaver;
import com.example.demo.utils.SerializingUtils;

import java.util.HashMap;
import java.util.List;

public class ModificationService implements ModificationServiceInterface {
    private static final int userId = 1;
    private final StorageService storageService;

    public ModificationService() {
        storageService = new StorageService();
    }

    public ModificationService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void removeLink(LinkModel link) throws ModificationServiceException, LinkServiceException {
        List<LinkModel> allLinks = storageService.getAllUserLinks(userId);
        if (!allLinks.contains(link)) throw new ModificationServiceException("Cannot remove not existing link");
        allLinks.remove(link);
        storageService.saveListOfLinks(userId, allLinks);
    }

    @Override
    public void changeLink(LinkModel oldLink,
                           String newName,
                           String newDescription,
                           String newURL)
            throws ModificationServiceException, LinkServiceException {
        removeLink(oldLink);
        LinkModel newLink = new LinkModel(newName, newURL, newDescription);
        storageService.saveLink(userId, newLink);
    }
}

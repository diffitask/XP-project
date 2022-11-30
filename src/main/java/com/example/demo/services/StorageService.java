package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.models.LinkServiceInterface;
import com.example.demo.models.MapSaver;
import com.example.demo.utils.SerializingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorageService implements LinkServiceInterface {
    private final String storageFileUrl;

    public StorageService(String storageFileUrl) {
        this.storageFileUrl = storageFileUrl;
    }

    @Override
    public List<LinkModel> getAllUserLinks(Integer userId) throws LinkServiceException {
        MapSaver mapSaver = SerializingUtils.deserializeStructure(storageFileUrl, MapSaver.class);
        return mapSaver.get(userId);
    }

    @Override
    public void saveLink(Integer userId, LinkModel linkModel) throws LinkServiceException {
        MapSaver mapSaver = SerializingUtils.deserializeStructure(storageFileUrl, MapSaver.class);
        List<LinkModel> userLinks = mapSaver.get(userId);

        if (userLinks.contains(linkModel)) {
            return;
        }
        
        userLinks.add(linkModel);
        mapSaver.put(userId, userLinks);
        SerializingUtils.serializeStructure(storageFileUrl, mapSaver);
    }
}

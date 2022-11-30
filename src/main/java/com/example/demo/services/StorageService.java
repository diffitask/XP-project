package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.models.MapSaver;
import com.example.demo.utils.SerializingUtils;

import java.util.HashMap;
import java.util.List;

public class StorageService implements StorageServiceInterface {
    private final String storageFileUrl;

    public StorageService() {
        this.storageFileUrl = "./src/main/java/com/example/demo/data-storage/users-link-lists-storage.json";
    }

    public StorageService(String storageFileUrl) {
        this.storageFileUrl = storageFileUrl;
    }

    public String getStorageFileUrl() {
        return storageFileUrl;
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


    @Override
    public void saveListOfLinks(int userId, List<LinkModel> linksList) throws LinkServiceException {
        MapSaver mapSaver = SerializingUtils.deserializeStructure(getStorageFileUrl(), MapSaver.class);
        HashMap<Integer, List<LinkModel>> usersLinkListMap = mapSaver.getUsersLinkListsMap();

        if (usersLinkListMap == null) {
            usersLinkListMap = new HashMap<>();
        }

        usersLinkListMap.put(userId, linksList);

        mapSaver.setUsersLinkListsMap(usersLinkListMap);
        SerializingUtils.serializeStructure(getStorageFileUrl(), mapSaver);
    }
}

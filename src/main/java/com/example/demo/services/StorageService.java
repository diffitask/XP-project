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
    private final String storageFileUrl = "./src/main/java/com/example/demo/data-storage/users-link-lists-storage.json";

    public StorageService() {}

    @Override
    public List<LinkModel> getAllUserLinks(Integer userId) throws LinkServiceException {
        MapSaver mapSaver = SerializingUtils.deserializeStructure(storageFileUrl, MapSaver.class);
        HashMap<Integer, List<LinkModel>> usersLinkListMap = mapSaver.getUsersLinkListsMap();

        if (usersLinkListMap != null && usersLinkListMap.containsKey(userId)) {
            return usersLinkListMap.get(userId);
        }

        return new ArrayList<>();
    }

    @Override
    public void saveLink(LinkModel linkModel) throws LinkServiceException {
        MapSaver mapSaver = SerializingUtils.deserializeStructure(storageFileUrl, MapSaver.class);
        HashMap<Integer, List<LinkModel>> usersLinkListMap = mapSaver.getUsersLinkListsMap();

        if (usersLinkListMap == null) {
            usersLinkListMap = new HashMap<>();
        }

        if (usersLinkListMap.containsKey(1) && usersLinkListMap.get(1).contains(linkModel)) {
            return;
        }

        // map editing
        // TODO: при расширении функционала передавать в метод userId текущего пользователя, сохраняющего ссылку
        int userId = 1;

        List<LinkModel> userLinkList = new ArrayList<>();
        if (usersLinkListMap.containsKey(userId)) {
            userLinkList = usersLinkListMap.get(userId);
        }

        userLinkList.add(linkModel);
        usersLinkListMap.put(userId, userLinkList);

        // saving new information
        mapSaver.setUsersLinkListsMap(usersLinkListMap);
        SerializingUtils.serializeStructure(storageFileUrl, mapSaver);
    }
}

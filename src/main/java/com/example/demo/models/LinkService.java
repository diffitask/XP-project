package com.example.demo.models;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.utils.SerializingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkService implements LinkServiceInterface {
    private final String storageFileUrl = "../data-storage/users-link-lists-storage.json";

    public LinkService() {}

    @Override
    public List<LinkModel> getAllUserLinks(Integer userId) throws LinkServiceException {
        HashMap<Integer, List<LinkModel>> usersLinkListMap = SerializingUtils.deserializeStructure(storageFileUrl, HashMap.class);

        if (usersLinkListMap.containsKey(userId)) {
            return usersLinkListMap.get(userId);
        }

        return null;
    }

    @Override
    public void saveLink(LinkModel linkModel) throws LinkServiceException {
        HashMap<Integer, List<LinkModel>> usersLinkListMap = SerializingUtils.deserializeStructure(storageFileUrl, HashMap.class);

        if (usersLinkListMap == null) {
            usersLinkListMap = new HashMap<>();
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
        SerializingUtils.serializeStructure(storageFileUrl, userLinkList);
    }
}

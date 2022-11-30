package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapSaver {
    private HashMap<Integer, List<LinkModel>> usersLinkListsMap;

    public List<LinkModel> get(Integer userId) {
        return usersLinkListsMap != null && usersLinkListsMap.containsKey(userId)
                ? usersLinkListsMap.get(userId)
                : new ArrayList<>();
    }

    public void put(Integer userId, List<LinkModel> userLinkList) {
        if (usersLinkListsMap == null) {
            usersLinkListsMap = new HashMap<>();
        }
        usersLinkListsMap.put(userId, userLinkList);
    }
}

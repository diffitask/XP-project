package com.example.demo.models;

import java.util.HashMap;
import java.util.List;

public class MapSaver {
    private HashMap<Integer, List<LinkModel>> usersLinkListsMap;

    public HashMap<Integer, List<LinkModel>> getUsersLinkListsMap() {
        return usersLinkListsMap;
    }

    public void setUsersLinkListsMap(HashMap<Integer, List<LinkModel>> usersLinkListsMap) {
        this.usersLinkListsMap = usersLinkListsMap;
    }
}

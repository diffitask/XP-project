package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;

import java.util.List;

public interface ModificationServiceInterface {

    void removeLink(LinkModel link) throws ModificationServiceException, LinkServiceException;
    void changeLink(LinkModel oldLink,
                    String newName,
                    String newDescription,
                    String newURL) throws ModificationServiceException, LinkServiceException;
    // todo move to Storage service
    void saveListOfLinks(int userId, List<LinkModel> linksList) throws LinkServiceException;
}

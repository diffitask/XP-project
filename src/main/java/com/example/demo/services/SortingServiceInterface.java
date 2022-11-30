package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;

import java.util.List;

public interface SortingServiceInterface {
    List<LinkModel> sortLinksByName(Integer userId) throws LinkServiceException;
    List<LinkModel> sortLinksByTag(Integer userId) throws LinkServiceException;
    List<LinkModel> sortLinksByDate(Integer userId) throws LinkServiceException;
}

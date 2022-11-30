package com.example.demo.controllers;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.requests.ChangeRequest;
import com.example.demo.services.ModificationService;
import com.example.demo.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModificationController {
    private final StorageService storageService = new StorageService();
    private final ModificationService modificationService = new ModificationService(storageService);

    // todo change html template
    @RequestMapping("/remove")
    public String removeLink(@ModelAttribute LinkModel linkModel, Model model) throws LinkServiceException, ModificationServiceException {
        modificationService.removeLink(linkModel);
        model.addAttribute("title", "Main page");
        model.addAttribute("links", storageService.getAllUserLinks(1));
        model.addAttribute("linkModel", new LinkModel());
        return "home";
    }

    // todo change html template
    @RequestMapping("/change")
    public String changeLink(@ModelAttribute ChangeRequest request, Model model) throws ModificationServiceException, LinkServiceException {
        modificationService.changeLink(request.getOldLink(),
                request.getNewName(),
                request.getNewDescription(),
                request.getNewUrl());
        model.addAttribute("title", "Main page");
        model.addAttribute("links", storageService.getAllUserLinks(1));
        model.addAttribute("linkModel", new LinkModel());
        return "home";
    }

}

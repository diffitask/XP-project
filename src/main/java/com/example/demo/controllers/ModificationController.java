package com.example.demo.controllers;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.services.ModificationService;
import com.example.demo.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModificationController {
    private final StorageService storageService = new StorageService();
    private final ModificationService modificationService = new ModificationService(storageService);

    @PostMapping("/remove")
    public String removeLink(@ModelAttribute LinkModel linkModel, Model model) throws LinkServiceException, ModificationServiceException {
        modificationService.removeLink(linkModel);
        model.addAttribute("title", "Main page");
        model.addAttribute("links", storageService.getAllUserLinks(1));
        model.addAttribute("linkModel", new LinkModel());
        return "home";
    }
}

package com.example.demo.controllers;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.services.StorageService;
import com.example.demo.models.LinkServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    private final LinkServiceInterface service = new StorageService("./src/main/java/com/example/demo/data-storage/users-link-lists-storage.json");
    private static final int userId = 1; // TODO to be removed after adding multiple users support

    @RequestMapping("/")
    public String home(@ModelAttribute LinkModel linkModel, Model model) throws LinkServiceException {
        service.saveLink(userId, linkModel);
        model.addAttribute("title", "Main page");
        model.addAttribute("links", service.getAllUserLinks(userId));
        model.addAttribute("linkModel", new LinkModel(userId));
        return "home";
    }
}

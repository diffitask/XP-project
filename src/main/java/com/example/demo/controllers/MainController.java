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
    //    private List<LinkModel> dummyLinks = new ArrayList<>(getDummyLinks());
    private final LinkServiceInterface service = new StorageService();

    @RequestMapping("/")
    public String home(@ModelAttribute LinkModel linkModel, Model model) throws LinkServiceException {
        service.saveLink(linkModel);
        model.addAttribute("title", "Main page");
        model.addAttribute("links", service.getAllUserLinks(1));
        model.addAttribute("linkModel", new LinkModel());
        return "home";
    }

    private List<LinkModel> getDummyLinks() {
        return List.of(
                new LinkModel("google",
                        "https://www.google.com",
                        "site google for searching"),
                new LinkModel("google1",
                        "google2.com",
                        "site google for searching"),
                new LinkModel("google2",
                        "google3.com",
                        "site google for searching"),
                new LinkModel("google3",
                        "google4.com",
                        "site google for searching"),
                new LinkModel("google5",
                        "google5.com",
                        "site google for searching"),
                new LinkModel("google6",
                        "google6.com",
                        "site google for searching")
        );
    }

}

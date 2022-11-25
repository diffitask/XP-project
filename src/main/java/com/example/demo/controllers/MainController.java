package com.example.demo.controllers;

import com.example.demo.models.LinkModel;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    // главная страница
    @GetMapping("/")
    // Model -- обязательный параметр.
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        model.addAttribute("links", getDummyLinks());
        // это шаблон home
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

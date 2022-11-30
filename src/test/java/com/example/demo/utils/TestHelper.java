package com.example.demo.utils;

import com.example.demo.models.LinkModel;
import lombok.Getter;

import java.util.List;

public class TestHelper {
    @Getter
    private static final String testStoragePath = "./src/test/java/com/example/demo/data-storage/users-link-lists-storage.json";

    public static List<LinkModel> getDummyLinks() {
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

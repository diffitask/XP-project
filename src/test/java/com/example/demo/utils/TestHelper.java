package com.example.demo.utils;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.services.StorageService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TestHelper {
    private static final int TEST_ID = 1;
    private static final String testStoragePath = "./src/test/java/com/example/demo/data-storage/users-link-lists-storage.json";

    public static int getTestId() {
        return TEST_ID;
    }

    public static String getTestStoragePath() {
        return testStoragePath;
    }

    public static LinkModel defaultLink(String suffix) {
        return new LinkModel(TEST_ID, "google" + suffix, "www.google.com", "search");
    }

    public static void setupEmptyTestStorage() {
        try (PrintWriter writer = new PrintWriter(testStoragePath)) {
            writer.print("{\n}");
        } catch (FileNotFoundException ignored) {
            System.exit(1);
        }
    }

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


    public static void fillDatabase(StorageService storageService) throws LinkServiceException {
        List<LinkModel> links = getDummyLinks();
        for (LinkModel link : getDummyLinks()) {
            storageService.saveLink(TEST_ID, link);
        }
    }
}

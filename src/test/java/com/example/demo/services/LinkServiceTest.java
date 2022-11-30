package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.models.LinkServiceInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LinkServiceTest {
    private static final int TEST_ID = 1;
    private static final String FILE_PATH = "./src/main/java/com/example/demo/data-storage/users-link-lists-storage.json";
    LinkServiceInterface service;

    @BeforeEach
    void setUp() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            writer.print("{\n}");
        } catch (FileNotFoundException ignored) {
            System.exit(1);
        }
        service = new StorageService(FILE_PATH);
    }

    @Test
    void testNothingSaved() throws LinkServiceException {
        Assertions.assertTrue(service.getAllUserLinks(TEST_ID).isEmpty());
    }

    @Test
    void testSaveSimple() throws LinkServiceException {
        service.saveLink(TEST_ID, defaultLink(""));
        List<LinkModel> userLinks = service.getAllUserLinks(TEST_ID);
        Assertions.assertEquals(1, userLinks.size());
        Assertions.assertEquals(defaultLink(""), userLinks.get(0));
    }

    @Test
    void testLinksOrder() throws LinkServiceException {
        List<LinkModel> expectedLinks = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            LinkModel toAdd = defaultLink(String.valueOf(i));
            expectedLinks.add(toAdd);
            service.saveLink(TEST_ID, toAdd);
        }
        List<LinkModel> actualLinks = service.getAllUserLinks(TEST_ID);
        Assertions.assertArrayEquals(expectedLinks.toArray(), actualLinks.toArray());
    }

    private LinkModel defaultLink(String suffix) {
        return new LinkModel(TEST_ID, "google" + suffix, "www.google.com", "search");
    }
}

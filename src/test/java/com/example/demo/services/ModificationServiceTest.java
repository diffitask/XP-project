package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.TestHelper.getDummyLinks;

public class ModificationServiceTest {
    private final int TEST_ID = 1;
    private final String storagePath = TestHelper.getTestStoragePath();

    ModificationServiceInterface service;
    StorageService storageService;

    @BeforeEach
    void setup() {
        storageService = new StorageService(storagePath);
        service = new ModificationService(storageService);
    }

    @BeforeEach
    void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(storagePath);
        writer.print("{\n}");
        writer.close();
    }

    @Test
    void testRemoveLink() throws LinkServiceException, ModificationServiceException {
        fillDatabase();

        List<LinkModel> oldLinks = storageService.getAllUserLinks(TEST_ID);
        List<LinkModel> expectedLinks = new ArrayList<>(oldLinks);

        for (LinkModel link : oldLinks) {
            service.removeLink(link);
            expectedLinks.remove(link);
            List<LinkModel> newLinks = storageService.getAllUserLinks(TEST_ID);
            Assertions.assertFalse(newLinks.contains(link));
            Assertions.assertTrue(expectedLinks.containsAll(newLinks));
        }
    }

    @Test
    void testChangeLink() throws LinkServiceException, ModificationServiceException {
        fillDatabase();
        LinkModel oldLink = getDummyLinks().get(0);
        LinkModel changedLink = new LinkModel("new-name",
                "new-description",
                "new-url");

        service.changeLink(oldLink,
                changedLink.getLinkName(),
                changedLink.getDescription(),
                changedLink.getLinkURL());

        List<LinkModel> links = storageService.getAllUserLinks(TEST_ID);
        Assertions.assertTrue(links.contains(changedLink));
        Assertions.assertFalse(links.contains(oldLink));
    }


    private void fillDatabase() throws LinkServiceException {
        List<LinkModel> links = getDummyLinks();
        for (LinkModel link : getDummyLinks()) {
            storageService.saveLink(TEST_ID, link);
        }
    }

}

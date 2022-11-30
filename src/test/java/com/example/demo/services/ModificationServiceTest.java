package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.exceptions.ModificationServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.TestHelper.fillDatabase;
import static com.example.demo.utils.TestHelper.getDummyLinks;

public class ModificationServiceTest {
    private final int TEST_ID = TestHelper.getTestId();
    private final String storagePath = TestHelper.getTestStoragePath();

    ModificationServiceInterface service;
    StorageService storageService;

    @BeforeEach
    void setup() {
        TestHelper.setupEmptyTestStorage();
        storageService = new StorageService(storagePath);
        service = new ModificationService(storageService);
    }

    @Test
    void testRemoveLink() throws LinkServiceException, ModificationServiceException {
        fillDatabase(storageService);

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
        fillDatabase(storageService);
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
}

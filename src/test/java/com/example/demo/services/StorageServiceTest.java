package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.TestHelper.defaultLink;

public class StorageServiceTest {
    private static final int TEST_ID = TestHelper.getTestId();
    StorageServiceInterface service;

    @BeforeEach
    void setUp() {
        TestHelper.setupEmptyTestStorage();
        service = new StorageService(TestHelper.getTestStoragePath());
    }

    @Test
    void testNothingSaved() throws LinkServiceException {
        Assertions.assertTrue(service.getAllUserLinks(TEST_ID).isEmpty());
    }

    @Test
    void testSaveSimple() throws LinkServiceException {
        LinkModel expected = defaultLink("");
        service.saveLink(TEST_ID, expected);
        List<LinkModel> userLinks = service.getAllUserLinks(TEST_ID);
        Assertions.assertEquals(1, userLinks.size());
        Assertions.assertEquals(expected, userLinks.get(0));
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
}

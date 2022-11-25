package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LinkServiceTest {
    private final int TEST_ID = 1;
    LinkServiceInterface service;

    @BeforeEach
    void setService() {
        // INSERT new IMPLEMENTATION OF SERVICE
        // service = new LinkServiceImpl();
    }

    @Test
    void testNothingSaved() {
        Assertions.assertTrue(service.getAllUserLinks(TEST_ID).isEmpty());
    }

    @Test
    void testSaveSimple() {
        service.saveLink(defaultLink(""));
        Assertions.assertEquals(defaultLink(""), service.getAllUserLinks(TEST_ID).get(0));
    }

    @Test
    void testLinksOrder() {
        List<LinkModel> expectedLinks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedLinks.add(defaultLink(String.valueOf(i)));
            service.saveLink(defaultLink(String.valueOf(i)));
        }
        List<LinkModel> actualLinks = service.getAllUserLinks(TEST_ID);
        Assertions.assertEquals(expectedLinks.size(), actualLinks.size());
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(expectedLinks.get(i), actualLinks.get(i));
        }
    }

    private LinkModel defaultLink(String suffix) {
        return new LinkModel(TEST_ID,"google" + suffix, "www.google.com", "search");
    }
}

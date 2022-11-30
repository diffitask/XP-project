package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import com.example.demo.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingServiceTest {
    private final int TEST_ID = 1;
    private StorageServiceInterface storageService;
    private SortingServiceInterface sortingService;

    @BeforeEach
    void setup() throws LinkServiceException {
        storageService = new StorageService(TestHelper.getTestStoragePath());
        sortingService = new SortingService(storageService);

        TestHelper.setupEmptyTestStorage();
        dataFilling();
    }

    @Test
    void testSortByName() throws LinkServiceException {
        List<String> expectedSortedNames = new ArrayList<>(Arrays.asList("a", "b", "x", "y"));
        List<String> actualSortedNames = sortingService.sortLinksByName(TEST_ID).stream()
                .map(LinkModel::getLinkName)
                .toList();

        Assertions.assertArrayEquals(expectedSortedNames.toArray(), actualSortedNames.toArray());
    }

    @Test
    void testSortByTag() throws LinkServiceException {
        List<String> expectedSortedTags = new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3", "tag4"));
        List<String> actualSortedTags = sortingService.sortLinksByTag(TEST_ID).stream()
                .map(LinkModel::getTag)
                .toList();

        Assertions.assertArrayEquals(expectedSortedTags.toArray(), actualSortedTags.toArray());
    }

    @Test
    void testSortByDate() throws LinkServiceException {
        List<String> expectedSortedDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2019, 1, 20),
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2021, 3, 20),
                LocalDate.of(2022, 4, 20)
        )).stream()
                .map(LocalDate::toString)
                .toList();
        List<String> actualSortedDates = sortingService.sortLinksByDate(TEST_ID).stream()
                .map(LinkModel::getCreationDate)
                .toList();

        Assertions.assertArrayEquals(expectedSortedDates.toArray(), actualSortedDates.toArray());
    }

    private void dataFilling() throws LinkServiceException {
        List<String> linksNames = new ArrayList<>(Arrays.asList("b", "x", "a", "y"));
        List<String> linksTags = new ArrayList<>(Arrays.asList("tag4", "tag1", "tag3", "tag2"));
        List<LocalDate> linksDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2022, 4, 20),
                LocalDate.of(2021, 3, 20),
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2019, 1, 20)
        ));

        for (int i = 0; i < linksNames.size(); i++) {
            storageService.saveLink(TEST_ID,
                    new LinkModel(TEST_ID,
                            linksNames.get(i),
                            "www.google.com",
                            "-",
                            linksTags.get(i),
                            linksDates.get(i)));
        }
    }
}

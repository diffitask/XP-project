package com.example.demo.services;

import com.example.demo.exceptions.LinkServiceException;
import com.example.demo.models.LinkModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingServiceTest {
    private final int TEST_ID = 1;
    private StorageServiceInterface storageService;
    private SortingServiceInterface sortingService;

    void setService() {
        storageService = new StorageService();
        sortingService = new SortingService(storageService);
    }

    void clearFile() throws FileNotFoundException {
        String FILE_PATH = "./src/main/java/com/example/demo/data-storage/users-link-lists-storage.json";
        PrintWriter writer = new PrintWriter(FILE_PATH);
        writer.print("{\n}");
        // other operations
        writer.close();
    }

    void dataFilling() throws LinkServiceException {
        List<String> linksNames = new ArrayList<>(Arrays.asList("b", "x", "a", "y"));
        List<String> linksTags = new ArrayList<>(Arrays.asList("tag4", "tag1", "tag3", "tag2"));
        List<LocalDate> linksDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2022, 4, 20),
                LocalDate.of(2021, 3, 20),
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2019, 1, 20)
        ));

        for (int i = 0; i < linksNames.size(); i++) {
            storageService.saveLink(new LinkModel(TEST_ID, linksNames.get(i), "www.google.com", "-", linksTags.get(i), linksDates.get(i)));
        }
    }

    @BeforeEach
    void preparation() throws FileNotFoundException, LinkServiceException {
        setService();
        clearFile();
        dataFilling();
    }

    @Test
    void testSortingNames() throws LinkServiceException {
        List<String> expectedSortedNames = new ArrayList<>(Arrays.asList("a", "b", "x", "y"));
        List<String> actualSortedNames = sortingService.sortLinksByName(TEST_ID).stream().map(LinkModel::getLinkName).toList();

        Assertions.assertEquals(expectedSortedNames.size(), actualSortedNames.size());
        for (int i = 0; i < expectedSortedNames.size(); i++) {
            Assertions.assertEquals(expectedSortedNames.get(i), actualSortedNames.get(i));
        }
    }

    @Test
    void testSortingTags() throws LinkServiceException {
        List<String> expectedSortedTags = new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3", "tag4"));
        List<String> actualSortedTags = sortingService.sortLinksByTag(TEST_ID).stream().map(LinkModel::getTag).toList();

        Assertions.assertEquals(expectedSortedTags.size(), actualSortedTags.size());
        for (int i = 0; i < expectedSortedTags.size(); i++) {
            Assertions.assertEquals(expectedSortedTags.get(i), actualSortedTags.get(i));
        }
    }

    @Test
    void testSortingDates() throws LinkServiceException {
        // sorting by dates
        List<String> expectedSortedDates = new ArrayList<>(Arrays.asList(
                LocalDate.of(2019, 1, 20),
                LocalDate.of(2020, 2, 20),
                LocalDate.of(2021, 3, 20),
                LocalDate.of(2022, 4, 20)
        )).stream().map(LocalDate::toString).toList();
        List<String> actualSortedDates = sortingService.sortLinksByDate(TEST_ID).stream().map(LinkModel::getCreationDate).toList();

        Assertions.assertEquals(expectedSortedDates.size(), actualSortedDates.size());
        for (int i = 0; i < expectedSortedDates.size(); i++) {
            Assertions.assertEquals(expectedSortedDates.get(i), actualSortedDates.get(i));
        }
    }

}
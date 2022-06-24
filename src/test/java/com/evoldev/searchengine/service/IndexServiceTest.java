package com.evoldev.searchengine.service;

import com.evoldev.searchengine.model.FileWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <h1>IndexServiceTest</h1>
 * Test class used for testing {@link IndexService} class.
 */
public class IndexServiceTest {

    /**
     * Path to the stored files for testing.
     */
    private static final String FILES_PATH = "src/test/resources";

    IndexService indexService = new IndexService();

    @Test
    public void testIndexFilesFromDirectory() {
        File file = new File(FILES_PATH);
        Map<String, List<FileWord>> filesDictionary = indexService.indexFilesFromDirectory(file);
        Assertions.assertEquals(filesDictionary.size(), 9);

    }
}

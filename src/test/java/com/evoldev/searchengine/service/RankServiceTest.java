package com.evoldev.searchengine.service;

import com.evoldev.searchengine.model.FileWord;
import com.evoldev.searchengine.utils.FileWordTestUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * <h1>RankServiceTest</h1>
 * Test class used for testing {@link RankService} class.
 */
public class RankServiceTest {

    private final RankService rankService = new RankService();

    @Test
    void rankWordsTest() {
        List<String> words = List.of("hi", "test");
        Map<String, List<FileWord>> fileDictionaries = FileWordTestUtils.createRandomFileDictionary();

        MatcherAssert.assertThat(rankService.rankWords(words, fileDictionaries), IsMapContaining.hasEntry("file2.txt", 100L));
        MatcherAssert.assertThat(rankService.rankWords(words, fileDictionaries), IsMapContaining.hasEntry("file1.txt", 50L));
    }

    @Test
    void rankWordsMaxTenResultsTest() {
        List<String> words = List.of("hi", "test");
        Map<String, List<FileWord>> fileDictionaries = FileWordTestUtils.createRandomFileDictionary();

        Assertions.assertEquals(rankService.rankWords(words, fileDictionaries).size(), 10);
    }
}

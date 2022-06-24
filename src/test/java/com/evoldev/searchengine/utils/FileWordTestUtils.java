package com.evoldev.searchengine.utils;

import com.evoldev.searchengine.model.FileWord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>FileWordTestUtils</h1>
 * Utils class for {@link FileWord}.
 */
public class FileWordTestUtils {

    /**
     * Creates a file dictionary for testing.
     * @return The file dictionary.
     */
    public static Map<String, List<FileWord>> createRandomFileDictionary () {
        Map<String, List<FileWord>> fileDictionaries = new HashMap<>();

        FileWord fileWord1 = new FileWord("file1.txt", "hi");
        FileWord fileWord2 = new FileWord("file2.txt", "hi");
        FileWord fileWord2v2 = new FileWord("file2.txt", "test");
        FileWord fileWord3 = new FileWord("file3.txt", "test");
        FileWord fileWord4 = new FileWord("file4.txt", "one");
        FileWord fileWord5 = new FileWord("file5.txt", "goal");
        FileWord fileWord6 = new FileWord("file6.txt", "best");
        FileWord fileWord7 = new FileWord("file7.txt", "king");
        FileWord fileWord8 = new FileWord("file8.txt", "queen");
        FileWord fileWord9 = new FileWord("file9.txt", "ceil");
        FileWord fileWord10 = new FileWord("file10.txt", "floor");
        FileWord fileWord11 = new FileWord("file11.txt", "beach");
        FileWord fileWord12 = new FileWord("file12.txt", "club");
        FileWord fileWord13 = new FileWord("file13.txt", "hotel");

        fileDictionaries.put("hi", List.of(fileWord1, fileWord2));
        fileDictionaries.put("test", List.of(fileWord2v2, fileWord3));
        fileDictionaries.put(fileWord4.getContent(), List.of(fileWord4));
        fileDictionaries.put(fileWord5.getContent(), List.of(fileWord5));
        fileDictionaries.put(fileWord6.getContent(), List.of(fileWord6));
        fileDictionaries.put(fileWord7.getContent(), List.of(fileWord7));
        fileDictionaries.put(fileWord8.getContent(), List.of(fileWord8));
        fileDictionaries.put(fileWord9.getContent(), List.of(fileWord9));
        fileDictionaries.put(fileWord10.getContent(), List.of(fileWord10));
        fileDictionaries.put(fileWord11.getContent(), List.of(fileWord11));
        fileDictionaries.put(fileWord12.getContent(), List.of(fileWord12));
        fileDictionaries.put(fileWord13.getContent(), List.of(fileWord13));

        return fileDictionaries;
    }
}

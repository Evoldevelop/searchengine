package com.evoldev.searchengine.service;

import com.evoldev.searchengine.model.FileWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <h1>IndexService</h1>
 * Service used for indexing words from a file.
 */
public class IndexService {

    /**
     * Index words from file by creating a usable dictionary.
     *
     * @param directory Directory containing all the files.
     * @return Dictionary with all the {@link FileWord} indexed.
     */
    public Map<String, List<FileWord>> indexFilesFromDirectory(File directory) {
        Stream<Map<String, FileWord>> fileWordsStream = Arrays.stream(directory.listFiles())
                .map(file -> {
                    try {
                        return createFileWordMap(file);
                    } catch (FileNotFoundException e) {
                        return Collections.emptyMap();
                    }
                });

        return streamMapToDictionary(fileWordsStream);
    }

    /**
     * Creates a {@link FileWord} map containing all the words from a file.
     *
     * @param file The file to be indexed.
     * @return The map containing all the words from the provided file.
     * @throws FileNotFoundException
     */
    private Map<String, FileWord> createFileWordMap(File file) throws FileNotFoundException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(file)));
        Map<String, FileWord> fileWordsIndex = new HashMap<>();
        while (s.hasNext()) {

            FileWord fileWord = null;
            String word = s.next();
            if (fileWordsIndex.containsKey(word)) {
                fileWord = fileWordsIndex.get(word);
            } else {
                fileWord = new FileWord(file.getName(), word);
            }

            fileWordsIndex.put(word, fileWord);
        }
        return fileWordsIndex;
    }

    /**
     * Converts the {@link Stream} map to a {@link Map} containing the list of {@link FileWord}
     * in every file for each word.
     *
     * @param fileWordsStream Stream to be converted.
     * @return the list of FileWords.
     */
    private Map<String, List<FileWord>> streamMapToDictionary(Stream<Map<String, FileWord>> fileWordsStream) {
        return fileWordsStream
                .filter(m -> !m.isEmpty())
                .flatMap(m -> m.values().stream())
                .collect(Collectors.groupingBy(FileWord::getContent,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
    }
}
package com.evoldev.searchengine.service;

import com.evoldev.searchengine.model.FileWord;
import com.evoldev.searchengine.utils.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h1>RankService</h1>
 * Service used for rank word appearances in a file.
 */
public class RankService {

    private static final Integer MAX_RESULTS = 10;

    /**
     * Ranks the words for each file.
     *
     * @param words             Words to be searched.
     * @param filesDictionaries Dictionary containing all the words indexed.
     * @return The filenames and their ranks.
     */
    public Map<String, Long> rankWords(List<String> words, Map<String, List<FileWord>> filesDictionaries) {
        Map<String, Long> fileWordCountAppearances = getWordAppearances(words, filesDictionaries);
        Map<String, Long> filenamesRanks = getCalculatedCoincidencePercentage(words, fileWordCountAppearances);

        return MapUtils.sortByValueDescendant(filenamesRanks);
    }

    /**
     * Gets the number of searched words that appear in every file.
     *
     * @param words             Words to be searched.
     * @param filesDictionaries Dictionary containing all the words indexed.
     * @return Word count appearances for each file.
     */
    private Map<String, Long> getWordAppearances(List<String> words, Map<String, List<FileWord>> filesDictionaries) {
        Map<String, Long> fileWordCountAppearances = new HashMap<>();

        List<FileWord> fileWordsWithAppearances = getFileWordsWithAppearances(filesDictionaries, words);
        fileWordsWithAppearances.forEach(fileWord -> {
            Long appearances = fileWordCountAppearances.getOrDefault(fileWord.getFilename(), 0L);
            fileWordCountAppearances.put(fileWord.getFilename(), ++appearances);
        });

        List<String> fileNamesWithoutAppearances = getFilenamesWithoutWordAppearances(filesDictionaries, fileWordCountAppearances);
        fileNamesWithoutAppearances.forEach(filename -> fileWordCountAppearances.put(filename, 0L));

        return fileWordCountAppearances.entrySet()
                .stream()
                .limit(MAX_RESULTS)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue)
                );
    }

    /**
     * Gets the {@link List} of {@link FileWord} that appear in each file.
     *
     * @param filesDictionaries Dictionary containing all the words indexed.
     * @param words             Words to be searched.
     * @return The list of FileWords that appear in each file.
     */
    private List<FileWord> getFileWordsWithAppearances(Map<String, List<FileWord>> filesDictionaries,
                                                       List<String> words) {
        return filesDictionaries.entrySet()
                .stream()
                .filter(entry -> words.contains(entry.getKey()))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }

    /**
     * Gets the filenames that do not have any word appearance.
     *
     * @param filesDictionaries        Dictionary containing all the words indexed.
     * @param fileWordCountAppearances {@link Map} containing each filename with its number of appearances.
     * @return A {@link List} of filenames that do not have any word appearance.
     */
    private List<String> getFilenamesWithoutWordAppearances(Map<String, List<FileWord>> filesDictionaries,
                                                            Map<String, Long> fileWordCountAppearances) {
        return filesDictionaries.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .map(FileWord::getFilename)
                .distinct()
                .filter(filename -> !fileWordCountAppearances.containsKey(filename))
                .collect(Collectors.toList());
    }

    /**
     * Calculates the coincidence percentage of each file.
     *
     * @param words                    Words to be searched.
     * @param fileWordCountAppearances Word count appearances for each file.
     * @return The filenames and their calculated coincidence percentage.
     */
    private Map<String, Long> getCalculatedCoincidencePercentage(List<String> words, Map<String, Long> fileWordCountAppearances) {
        return fileWordCountAppearances.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, entry -> calculateCoincidencePercentage(words.size(), entry.getValue())
                ));
    }

    /**
     * Calculates the coincidence percentage.
     *
     * @param total       Total number of searched words.
     * @param actualCount Actual number of searched words found.
     * @return The coindicende percentage.
     */
    private Long calculateCoincidencePercentage(Integer total, Long actualCount) {
        return (actualCount * 100) / total;
    }
}

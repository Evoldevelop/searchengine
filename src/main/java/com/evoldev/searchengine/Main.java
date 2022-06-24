package com.evoldev.searchengine;

import com.evoldev.searchengine.model.FileWord;
import com.evoldev.searchengine.service.IndexService;
import com.evoldev.searchengine.service.PrintService;
import com.evoldev.searchengine.service.RankService;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        final File indexableDirectory = new File(args[0]);
        directoryValidations(indexableDirectory);

        IndexService indexService = new IndexService();
        Map<String, List<FileWord>> filesDictionaries = indexService.indexFilesFromDirectory(indexableDirectory);

        PrintService.printMessage(indexableDirectory.listFiles().length + " files read in directory "
                + indexableDirectory.getPath());

        executeRank(filesDictionaries);
    }

    /**
     * Executes the ranking process.
     *
     * @param filesDictionaries Dictionary containing all the words indexed.
     */
    private static void executeRank(Map<String, List<FileWord>> filesDictionaries) {
        RankService rankService = new RankService();

        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                PrintService.printMessage("search> ");
                final String line = keyboard.nextLine();

                if(line.equals("quit")) System.exit(0);

                List<String> words = Arrays.asList(line.split(" "));
                Map<String, Long> ranks = rankService.rankWords(words, filesDictionaries);

                ranks.entrySet()
                        .forEach((entry) -> PrintService.printMessage(entry.getKey() + ": " + entry.getValue() + "%"));
            }
        }
    }

    /**
     * Validates the directory.
     *
     * @param directory Directory to be validated.
     */
    private static void directoryValidations(File directory) {
        if (directory.listFiles() == null) {
            throw new NullPointerException("No directory found with name \""
                    + directory.getName() + "\"");
        }

        if (directory.listFiles().length == 0) {
            throw new NullPointerException("No files found in directory \""
                    + directory.getName() + "\"");
        }
    }
}

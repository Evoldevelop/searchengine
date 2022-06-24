package com.evoldev.searchengine.service;

/**
 * <h1>PrintService</h1>
 * Service that prints messages.
 * Decouples System from the business logic.
 */
public class PrintService {

    /**
     * Prints a {@link String} in console.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}

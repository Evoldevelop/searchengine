package com.evoldev.searchengine.model;

public final class FileWord {

    private final String filename;
    private final String content;

    public FileWord(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }
}

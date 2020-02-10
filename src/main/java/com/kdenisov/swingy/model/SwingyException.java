package com.kdenisov.swingy.model;

public class SwingyException extends Exception {
    private String errorMessage;

    public SwingyException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}

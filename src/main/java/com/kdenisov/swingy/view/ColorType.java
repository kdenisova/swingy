package com.kdenisov.swingy.view;

public enum ColorType {
    RESET("\033[0m"),
    BLACK("\033[0;30m"),
    RED("\033[1;91m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[1;94m"),
    MAGENTA("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m");

    private final String code;

    ColorType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

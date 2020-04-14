package com.kdenisov.swingy.model;

import java.util.HashSet;
import java.util.Set;

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

//
//public class CustomValidationException extends Exception {
//    public Set<String> errorMessages = new HashSet<>();
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//        for (String errorMessage : errorMessages) {
//            sb.append(errorMessage);
//            sb.append("\r");
//        }
//        return sb.toString();
//    }
//}
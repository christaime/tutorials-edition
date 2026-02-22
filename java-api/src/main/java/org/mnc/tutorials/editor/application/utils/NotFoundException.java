package org.mnc.tutorials.editor.application.utils;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

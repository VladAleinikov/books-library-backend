package org.example.bookslibrary.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not found.");
    }
}

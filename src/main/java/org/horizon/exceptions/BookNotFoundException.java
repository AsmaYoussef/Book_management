package org.horizon.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String category) {
        super("Book with category " + category + " does not exist");
    }
}

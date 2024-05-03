package ru.phonemarket.mobile.exception;
/**
 * This exception is thrown when an entity is not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

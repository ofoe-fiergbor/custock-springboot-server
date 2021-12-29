package com.davinci.custockspringbootserver.exceptions;

public class InsufficientProductQuantityException extends Exception {
    public InsufficientProductQuantityException(String message) {
        super(message);
    }
}

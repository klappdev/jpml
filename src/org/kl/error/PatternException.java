package org.kl.error;

public class PatternException extends RuntimeException {
    private final String message;

    public PatternException() {
        this.message = "";
    }

    public PatternException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

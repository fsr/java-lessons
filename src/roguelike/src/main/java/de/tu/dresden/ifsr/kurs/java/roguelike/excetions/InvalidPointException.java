package de.tu.dresden.ifsr.kurs.java.roguelike.excetions;

public class InvalidPointException extends RuntimeException {
    public InvalidPointException() {
        super();
    }

    public InvalidPointException(String message) {
        super(message);
    }

    public InvalidPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPointException(Throwable cause) {
        super(cause);
    }

    protected InvalidPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

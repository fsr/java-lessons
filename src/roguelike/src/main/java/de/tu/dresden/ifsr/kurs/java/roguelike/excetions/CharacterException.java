package de.tu.dresden.ifsr.kurs.java.roguelike.excetions;

public class CharacterException extends Exception {

    public CharacterException() {
        super();
    }

    public CharacterException(String message) {
        super(message);
    }

    public CharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterException(Throwable cause) {
        super(cause);
    }

    protected CharacterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

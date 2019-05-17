package Errors;

public class LexerException extends Exception {

    private final String message;

    public LexerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

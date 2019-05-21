package Errors;

public class LexerException extends LangException {

    private final String message;

    public LexerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

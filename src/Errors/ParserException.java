package Errors;

public class ParserException extends Exception {

    private final String message;

    public ParserException(String message) {
        this.message = message;
    }

    public ParserException(String message, String name, int line) {
        this.message = message + " " + name + ":" + line;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

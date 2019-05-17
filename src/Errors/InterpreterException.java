package Errors;

public class InterpreterException extends Exception {

    private final String message;

    public InterpreterException(String message) {
        this.message = message;
    }

    public InterpreterException(String message, System name, int line) {
        this.message = message + " " + name + ":" + line;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

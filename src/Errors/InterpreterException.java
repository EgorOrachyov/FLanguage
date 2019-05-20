package Errors;

import Lexing.Token;

public class InterpreterException extends ASTException {

    private final String message;

    public InterpreterException(String message, Token token) {
        this.message = message + " " + token.getLexeme() + ":" + token.getLine();
    }

    public InterpreterException(String message, String name, int line) {
        this.message = message + " " + name + ":" + line;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

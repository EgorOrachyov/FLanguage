package Errors;

import Language.FunctionDefinition;
import Visitors.PrintVisitor;

public class FunctionInfoException extends RuntimeException {

    private final String message;

    public FunctionInfoException(String message, FunctionDefinition function) {
        this.message = message + " " + (new PrintVisitor().visit(function)) + ":" + function.name.getLine();
    }

    public String getMessage() {
        return message;
    }
}

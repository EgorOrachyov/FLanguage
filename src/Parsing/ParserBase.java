package Parsing;

import Errors.ErrorMessage;
import Errors.ParserException;
import Lexing.Token;

import java.util.List;

public class ParserBase {

    private Token c;
    private int current = 0;
    private final List<Token> tokens;

    protected ParserBase(List<Token>  tokens) {
        this.tokens = tokens;
        c = tokens.get(current);
    }

    protected Token advance() {
        current += 1;
        if (!end()) {
            c = tokens.get(current);
        } else {
            c = null;
        }
        return tokens.get(current - 1);
    }

    protected Token current() throws ParserException {
        if (end()) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }
        return tokens.get(current);
    }

    protected void setIndex(int index) {
        current = index;
        if (!end()) {
            c = tokens.get(current);
        } else {
            c = null;
        }
    }

    protected boolean end() {
        return current >= tokens.size();
    }

    protected int index() {
        return current;
    }

}

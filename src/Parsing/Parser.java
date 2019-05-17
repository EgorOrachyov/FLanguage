package Parsing;

import Errors.ErrorMessage;
import Errors.ParserException;
import Language.*;
import Lexing.Token;
import Lexing.TokenType;
import Utils.Carriage;

import java.util.List;

public class Parser {

    private Program program;
    private final Carriage<Token> tokens;

    public Parser(List<Token> tokens) {
        Token[] source = (Token[]) tokens.toArray();
        this.tokens = new Carriage<>(source);
    }

    public Program getProgram() {
        return program;
    }

    public void run() throws ParserException {
        final int index = tokens.index();
        ParserException exception = null;
        FunctionDefinitionList funDefList = null;

        try {
            funDefList = functionDefinitionList();
        } catch (ParserException e) {
            exception = e;
        }

        if (exception == null) {
            tokens.setIndex(index);
            Expression body = expression();
            program = new Program(funDefList, body);
        } else {
            Expression body = expression();
            program = new Program(body);
        }
    }

    private FunctionDefinitionList functionDefinitionList() throws ParserException {
        final int index = tokens.index();
        ParserException first = null;
        ParserException second = null;

        try {
            FunctionDefinition funDef = functionDefinition();
        } catch (ParserException e) {
            first = e;
        }

        if (first == null) {

        }

        return null;
    }

    private FunctionDefinition functionDefinition() throws ParserException {
        if (!checkType(TokenType.IDENTIFIER)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token name = tokens.current();
        tokens.advance();

        if (!matchAny(TokenType.LEFT_BRACKET)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        ParamsList params = paramsList();

        if (!matchAll(TokenType.RIGHT_BRACKET, TokenType.EQUAL, TokenType.LEFT_FIGURE_BRACKET)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Expression body = expression();

        return new FunctionDefinition(name, params, body);
    }

    private ParamsList paramsList() throws ParserException {
        if (!checkType(TokenType.IDENTIFIER)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token var = tokens.current();
        tokens.advance();

        if (matchAny(TokenType.COMMA)) {
            ParamsList params = paramsList();
            return new ParamsList(var, params);
        } else {
            return new ParamsList(var);
        }
    }

    private Expression expression() throws ParserException {
        return null;
    }

    private boolean matchAny(TokenType ... types) {
        for (TokenType type : types) {
            if (checkType(type)) {
                tokens.advance();
                return true;
            }
        }
        tokens.advance();
        return false;
    }

    private boolean matchAll(TokenType ... types) {
        for (TokenType type : types) {
            if (!tokens.end() && checkType(type)) {
                tokens.advance();
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkType(TokenType type) {
        return tokens.current().getType().equals(type);
    }





}

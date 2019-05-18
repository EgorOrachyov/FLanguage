package Parsing;

import Errors.ErrorMessage;
import Errors.ParserException;
import Language.*;
import Lexing.Token;
import Lexing.TokenType;
import Visitors.PrintVisitor;

import java.util.List;

public class Parser extends ParserBase {

    private Program program;

    public Parser(List<Token> tokens) {
        super(tokens);
    }

    public Program getProgram() {
        return program;
    }

    public void run() throws ParserException {
        final int index = index();
        try {
            FunctionDefinitionList funDefList = functionDefinitionList();
            Expression body = expression();
            program = new Program(funDefList, body);
        } catch (ParserException e) {
            setIndex(index);
            Expression body = expression();
            program = new Program(body);
        }

        if (!end()) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }
    }

    private FunctionDefinitionList functionDefinitionList() throws ParserException {
        FunctionDefinition funDef = functionDefinition();
        matchAny(TokenType.EOL);

        final int index = index();

        try {
            FunctionDefinitionList defList = functionDefinitionList();
            return new FunctionDefinitionList(funDef, defList);
        } catch (ParserException e) {
            setIndex(index);
            return new FunctionDefinitionList(funDef);
        }
    }

    private FunctionDefinition functionDefinition() throws ParserException {
        if (!checkType(TokenType.IDENTIFIER)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token name = advance();

        matchAny(TokenType.LEFT_BRACKET);

        ParamsList params = paramsList();

        matchAll(TokenType.RIGHT_BRACKET, TokenType.EQUAL, TokenType.LEFT_FIGURE_BRACKET);

        Expression body = expression();

        matchAny(TokenType.RIGHT_FIGURE_BRACKET);

        return new FunctionDefinition(name, params, body);
    }

    private ParamsList paramsList() throws ParserException {
        if (!checkType(TokenType.IDENTIFIER)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token var = advance();

        if (checkType(TokenType.COMMA)) {
            advance();
            ParamsList params = paramsList();
            return new ParamsList(var, params);
        } else {
            return new ParamsList(var);
        }
    }

    private Expression expression() throws ParserException {
        if (checkType(TokenType.LEFT_BRACKET)) {
            BinaryExpression binExpr = binaryExpression();
            return new Expression(binExpr);
        }

        if (checkType(TokenType.LEFT_SQUARE_BRACKET)) {
            IfExpression ifExpr = ifExpression();
            return new Expression(ifExpr);
        }

        if (checkType(TokenType.IDENTIFIER)) {
            final int index = index();
            Token identifier = advance();
            if (checkType(TokenType.LEFT_BRACKET)) {
                setIndex(index);
                CallExpression callExpr = callExpression();
                return new Expression(callExpr);
            } else {
                return new Expression(identifier);
            }
        }

        if (checkType(TokenType.NUMBER)) {
            Token num = advance();
            return new Expression(num);
        }

        matchAny(TokenType.MINUS);
        if (checkType(TokenType.NUMBER)) {
            Token num = advance();
            return new Expression(new Token(TokenType.NUMBER, "-" + num.getLexeme(), num.getLine()));
        } else {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }
    }

    private BinaryExpression binaryExpression() throws ParserException {
        matchAny(TokenType.LEFT_BRACKET);

        Expression expression1 = expression();

        if (!checkTypes(TokenType.PLUS, TokenType.MINUS,
                TokenType.STAR, TokenType.SLASH,
                TokenType.PERCENT, TokenType.GREATER,
                TokenType.LESS, TokenType.EQUAL)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token operation = advance();

        Expression expression2 = expression();

        matchAny(TokenType.RIGHT_BRACKET);

        return new BinaryExpression(expression1, operation, expression2);
    }

    private CallExpression callExpression() throws ParserException {
        if (!checkType(TokenType.IDENTIFIER)) {
            throw new ParserException(ErrorMessage.SYNTAX_ERROR);
        }

        Token functionName = advance();

        matchAny(TokenType.LEFT_BRACKET);

        ArgumentsList argList = argumentsList();

        matchAny(TokenType.RIGHT_BRACKET);

        return new CallExpression(functionName, argList);
    }

    private IfExpression ifExpression() throws ParserException {
        matchAny(TokenType.LEFT_SQUARE_BRACKET);

        Expression condition = expression();

        matchAll(TokenType.RIGHT_SQUARE_BRACKET, TokenType.QUESTION, TokenType.LEFT_FIGURE_BRACKET);

        Expression ifTrue = expression();

        matchAll(TokenType.RIGHT_FIGURE_BRACKET, TokenType.COLON, TokenType.LEFT_FIGURE_BRACKET);

        Expression ifFalse = expression();

        matchAll(TokenType.RIGHT_FIGURE_BRACKET);

        return new IfExpression(condition, ifTrue, ifFalse);
    }

    private ArgumentsList argumentsList() throws ParserException {
        Expression argument = expression();

        if (checkType(TokenType.COMMA)) {
            advance();
            ArgumentsList list = argumentsList();
            return new ArgumentsList(argument, list);
        } else {
            return new ArgumentsList(argument);
        }
    }

    private void matchAny(TokenType ... types) throws ParserException {
        for (TokenType type : types) {
            if (checkType(type)) {
                advance();
                return;
            }
        }
        throw new ParserException(ErrorMessage.SYNTAX_ERROR);
    }

    private void matchAll(TokenType ... types) throws ParserException {
        for (TokenType type : types) {
            if (checkType(type)) {
                advance();
            } else {
                throw new ParserException(ErrorMessage.SYNTAX_ERROR);
            }
        }
    }

    private boolean checkType(TokenType type) throws ParserException {
        return (!end() && current().getType().equals(type));
    }

    private boolean checkTypes(TokenType ... types) throws ParserException {
        for (TokenType type : types) {
            if (checkType(type)) {
                return true;
            }
        }
        return false;
    }
}

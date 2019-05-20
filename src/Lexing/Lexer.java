package Lexing;

import Errors.ErrorMessage;
import Errors.LexerException;
import Utils.Carriage;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private int line = 0;
    private final Carriage source;
    private final List<Token> tokens = new ArrayList<>();

    public Lexer(Carriage carriage) {
        this.source = carriage;
    }

    public void run() throws LexerException {
        while (!source.end()) {
            match();
            source.advance();
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void match() throws LexerException {

        char c = source.current();

        if (c >= '0' && c <= '9') {
            number();
            return;
        }

        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '_') {
            identifier();
            return;
        }

        switch (c) {

            case '*': addToken(TokenType.STAR);
                break;
            case '/': addToken(TokenType.SLASH);
                break;
            case '+': addToken(TokenType.PLUS);
                break;
            case '-': addToken(TokenType.MINUS);
                break;
            case '%': addToken(TokenType.PERCENT);
                break;

            case '<': addToken(TokenType.LESS);
                break;
            case '>': addToken(TokenType.GREATER);
                break;
            case '=': addToken(TokenType.EQUAL);
                break;

            case '\n': addToken(TokenType.EOL); line += 1;
                break;
            case '?': addToken(TokenType.QUESTION);
                break;
            case ',': addToken(TokenType.COMMA);
                break;
            case ':': addToken(TokenType.COLON);
                break;

            case '(': addToken(TokenType.LEFT_BRACKET);
                break;
            case ')': addToken(TokenType.RIGHT_BRACKET);
                break;
            case '{': addToken(TokenType.LEFT_FIGURE_BRACKET);
                break;
            case '}': addToken(TokenType.RIGHT_FIGURE_BRACKET);
                break;
            case '[': addToken(TokenType.LEFT_SQUARE_BRACKET);
                break;
            case ']': addToken(TokenType.RIGHT_SQUARE_BRACKET);
                break;

            default:
                throw new LexerException(ErrorMessage.SYNTAX_ERROR);

        }
    }

    private void identifier() {
        int start = source.index();

        while (!source.end() && isCharacter(source.current())) {
            source.advance();
        }

        int end = source.index();
        String lexeme = source.subString(start, end);
        addToken(TokenType.IDENTIFIER, lexeme);

        source.offsetBack();
    }

    private void number() {
        int start = source.index();

        while (!source.end() && isDigit(source.current())) {
            source.advance();
        }

        int end = source.index();
        String lexeme = source.subString(start, end);
        addToken(TokenType.NUMBER, lexeme);

        source.offsetBack();
    }

    private void addToken(TokenType type) {
        tokens.add(new Token(type, null, line));
    }

    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme, line));
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isCharacter(char c) {
        return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '_');
    }


}

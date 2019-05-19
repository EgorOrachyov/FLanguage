package Language;

import Lexing.Token;
import Lexing.TokenType;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class Expression extends ASTNode {

    public enum Type {
        IDENTIFIER,
        NUMBER,
        BIN_EXPR,
        IF_EXPR,
        CALL_EXPR
    }

    public Type type;
    public final Token token;
    public final ASTNode expression;

    public Expression(Token t) {
        token = t;
        expression = null;

        if (t.getType().equals(TokenType.IDENTIFIER)) {
            type = Type.IDENTIFIER;
        } else {
            type = Type.NUMBER;
        }
    }

    public Expression(BinaryExpression e) {
        type = Type.BIN_EXPR;
        token = null;
        expression = e;
    }

    public Expression(CallExpression e) {
        type = Type.CALL_EXPR;
        token = null;
        expression = e;
    }

    public Expression(IfExpression e) {
        type = Type.IF_EXPR;
        token = null;
        expression = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
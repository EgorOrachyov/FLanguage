package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Visitors.ASTVisitor;

public class BinaryExpression extends ASTNode {

    public final Expression expr1;
    public final Expression expr2;
    public final Token operator;

    public BinaryExpression(Expression expr1, Token operator, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public int line() {
        return operator.getLine();
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

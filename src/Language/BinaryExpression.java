package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class BinaryExpression extends ASTNode {

    public final ASTNode expr1;
    public final ASTNode expr2;
    public final Token operator;

    public BinaryExpression(Language.Expression e1, Token o, Language.Expression e2) {
        expr1 = e1;
        expr2 = e2;
        operator = o;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

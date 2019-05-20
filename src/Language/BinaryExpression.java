package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class BinaryExpression extends ASTNode {

    public final ASTNode expr1;
    public final ASTNode expr2;
    public final Token operator;

    public BinaryExpression(Language.Expression expr1, Token operator, Language.Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

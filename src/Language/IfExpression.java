package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class IfExpression extends ASTNode {

    public final Expression condition;
    public final Expression ifTrue;
    public final Expression ifFalse;

    public IfExpression(Language.Expression c, Language.Expression t, Language.Expression f) {
        condition = c;
        ifTrue = t;
        ifFalse = f;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

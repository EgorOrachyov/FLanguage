package Language;

import Parsing.ASTNode;
import Parsing.ASTVisitor;

public class IfExpression extends ASTNode {

    public final Expression condition;
    public final Expression ifTrue;
    public final Expression ifFalse;

    public IfExpression(Language.Expression condition, Language.Expression ifTrue, Language.Expression ifFalse) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public int line() {
        return condition.line();
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

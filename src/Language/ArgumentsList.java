package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class ArgumentsList extends ASTNode {

    public enum Type {
        EXPRESSION,
        EXPRESSION_AND_LIST
    }

    public final Type type;
    public final Expression expression;
    public final ArgumentsList list;

    public ArgumentsList(Language.Expression e) {
        super(ASTNodeType.ARG_LIST);
        type = ArgumentsList.Type.EXPRESSION;
        expression = e;
        list = null;
    }

    public ArgumentsList(Language.Expression e, ArgumentsList l) {
        super(ASTNodeType.ARG_LIST);
        type = ArgumentsList.Type.EXPRESSION_AND_LIST;
        expression = e;
        list = l;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

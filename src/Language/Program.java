package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class Program extends ASTNode {

    public enum Type {
        EXPRESSION,
        DEF_LIST_AND_EXPRESSION
    }

    public final Type type;
    public final FunctionDefinitionList definitions;
    public final Expression expression;

    public Program(Expression main) {
        super(ASTNodeType.PROGRAM);
        type = Type.EXPRESSION;
        definitions = null;
        expression = main;
    }

    public Program(FunctionDefinitionList list, Expression main) {
        super(ASTNodeType.PROGRAM);
        type = Type.DEF_LIST_AND_EXPRESSION;
        definitions = list;
        expression = main;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class Program extends ASTNode {

    public final FunctionDefinitionList definitions;
    public final Expression expression;

    public Program(FunctionDefinitionList list, Expression main) {
        super(ASTNodeType.PROGRAM);
        definitions = list;
        expression = main;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

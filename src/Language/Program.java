package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public abstract class Program extends ASTNode {

    public static class Body extends Program {

        public final Expression expression;

        public Body(Expression body) {
            expression = body;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class BodyDefinitionList extends Program {

        public final FunctionDefinitionList definitions;
        public final Expression expression;

        public BodyDefinitionList(FunctionDefinitionList definitionList, Expression body) {
            expression = body;
            definitions = definitionList;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}

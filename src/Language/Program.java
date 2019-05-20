package Language;

import Parsing.ASTNode;
import Visitors.ASTVisitor;

public abstract class Program extends ASTNode {

    public static class Body extends Program {

        public final Expression expression;

        public Body(Expression body) {
            expression = body;
        }

        @Override
        public int line() {
            return expression.line();
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
        public int line() {
            return definitions.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}

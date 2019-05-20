package Language;

import Parsing.ASTNode;
import Visitors.ASTVisitor;

public abstract class FunctionDefinitionList extends ASTNode {

    public static class Definition extends FunctionDefinitionList {

        public final FunctionDefinition funDef;

        public Definition(FunctionDefinition funDef) {
            this.funDef = funDef;
        }

        @Override
        public int line() {
            return funDef.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class DefinitionAndList extends FunctionDefinitionList {

        public final FunctionDefinition funDef;
        public final FunctionDefinitionList funDefList;

        public DefinitionAndList(FunctionDefinition funDef, FunctionDefinitionList funDefList) {
            this.funDef = funDef;
            this.funDefList = funDefList;
        }

        @Override
        public int line() {
            return funDef.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}

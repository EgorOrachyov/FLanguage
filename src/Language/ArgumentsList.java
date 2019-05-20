package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public abstract class ArgumentsList extends ASTNode {

    public static class Argument extends ArgumentsList {

        public final Expression arg;

        public Argument(Expression arg) {
            this.arg = arg;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class ArgumentAndList extends ArgumentsList {

        public final Expression arg;
        public final ArgumentsList list;

        public ArgumentAndList(Expression arg, ArgumentsList list) {
            this.arg = arg;
            this.list = list;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}

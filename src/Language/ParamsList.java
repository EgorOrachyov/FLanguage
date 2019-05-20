package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTVisitor;

public abstract class ParamsList extends ASTNode {

    public static class OneParam extends ParamsList {

        public final Token param;

        public OneParam(Token param) {
            this.param = param;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class OneParamAndList extends ParamsList {

        public final Token param;
        public final ParamsList list;

        public OneParamAndList(Token param, ParamsList list) {
            this.param = param;
            this.list = list;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}

package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class ParamsList extends ASTNode {

    public enum Type {
        IDENTIFIER,
        IDENTIFIER_AND_LIST
    }

    public final Type type;
    public final Token token;
    public final ParamsList list;

    public ParamsList(Token t) {
        type = Type.IDENTIFIER;
        token = t;
        list = null;
    }

    public ParamsList(Token t, ParamsList l) {
        type = Type.IDENTIFIER_AND_LIST;
        token = t;
        list = l;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class CallExpression extends ASTNode {

    public final Token token;
    public final ArgumentsList list;

    public CallExpression(Token t, ArgumentsList l) {
        super(ASTNodeType.CALL_EXPR);
        token = t;
        list = l;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

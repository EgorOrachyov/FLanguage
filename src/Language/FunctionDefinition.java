package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class FunctionDefinition extends ASTNode {

    public final Token identifier;
    public final ParamsList paramsList;
    public final Expression funBody;

    public FunctionDefinition(Token id, ParamsList params, Expression body) {
        identifier = id;
        paramsList = params;
        funBody = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

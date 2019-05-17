package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

import java.lang.reflect.ParameterizedType;

public class FunctionDefinition extends ASTNode {

    public final Token identifier;
    public final ParamsList paramsList;
    public final Expression funBody;

    public FunctionDefinition(Token id, ParamsList params, Expression body) {
        super(ASTNodeType.FUN_DEF);
        identifier = id;
        paramsList = params;
        funBody = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

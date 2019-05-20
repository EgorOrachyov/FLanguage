package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTVisitor;

public class FunctionDefinition extends ASTNode {

    public final Token name;
    public final ParamsList paramsList;
    public final Expression funBody;

    public FunctionDefinition(Token name, ParamsList paramsList, Expression funBody) {
        this.name = name;
        this.paramsList = paramsList;
        this.funBody = funBody;
    }

    @Override
    public int line() {
        return name.getLine();
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

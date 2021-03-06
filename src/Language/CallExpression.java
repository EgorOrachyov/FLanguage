package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Visitors.ASTVisitor;

public class CallExpression extends ASTNode {

    public final Token name;
    public final ArgumentsList arguments;

    public CallExpression(Token name, ArgumentsList arguments) {
        this.name = name;
        this.arguments = arguments;
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

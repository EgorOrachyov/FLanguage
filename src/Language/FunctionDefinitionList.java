package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class FunctionDefinitionList extends ASTNode {

    public enum Type {
        EMPTY,
        ONE,
        ONE_AND_LIST
    }

    public final Type type;
    public final FunctionDefinition function;
    public final FunctionDefinitionList list;

    public FunctionDefinitionList() {
        super(ASTNodeType.FUN_DEF_LIST);
        type = Type.EMPTY;
        function = null;
        list = null;
    }

    public FunctionDefinitionList(FunctionDefinition definition, Token t) {
        super(ASTNodeType.FUN_DEF_LIST);
        type = Type.ONE;
        function = definition;
        list = null;
    }

    public FunctionDefinitionList(FunctionDefinition definition, Token t, FunctionDefinitionList others) {
        super(ASTNodeType.FUN_DEF_LIST);
        type = Type.ONE_AND_LIST;
        function = definition;
        list = others;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

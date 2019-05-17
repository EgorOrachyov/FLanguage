package Language;

import Parsing.ASTNode;
import Parsing.ASTNodeType;
import Parsing.ASTVisitor;

public class FunctionDefinitionList extends ASTNode {

    public enum Type {
        ONE,
        ONE_AND_LIST
    }

    public final Type type;
    public final FunctionDefinition function;
    public final FunctionDefinitionList list;

    public FunctionDefinitionList(FunctionDefinition definition) {
        super(ASTNodeType.FUN_DEF_LIST);
        type = Type.ONE;
        function = definition;
        list = null;
    }

    public FunctionDefinitionList(FunctionDefinition definition, FunctionDefinitionList others) {
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

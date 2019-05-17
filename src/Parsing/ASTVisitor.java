package Parsing;

import Language.*;

public interface ASTVisitor<T> {

    public T visit(Expression expression);

    public T visit(BinaryExpression expression);

    public T visit(ArgumentsList arguments);

    public T visit(IfExpression expression);

    public T visit(CallExpression call);

    public T visit(ParamsList params);

    public T visit(FunctionDefinition function);

    public T visit(FunctionDefinitionList definitionList);

    public T visit(Program entry);


}

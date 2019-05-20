package Parsing;

import Language.*;

public interface ASTVisitor<T> {

    public T visit(Expression expression);

    public T visit(Expression.Var expression);

    public T visit(Expression.Num expression);

    public T visit(Expression.Binary expression);

    public T visit(Expression.Call expression);

    public T visit(Expression.If expression);

    public T visit(BinaryExpression expression);

    public T visit(ArgumentsList arguments);

    public T visit(ArgumentsList.Argument arguments);

    public T visit(ArgumentsList.ArgumentAndList arguments);

    public T visit(IfExpression expression);

    public T visit(CallExpression call);

    public T visit(ParamsList params);

    public T visit(ParamsList.OneParam params);

    public T visit(ParamsList.OneParamAndList params);

    public T visit(FunctionDefinition function);

    public T visit(FunctionDefinitionList definitionList);

    public T visit(FunctionDefinitionList.Definition definitionList);

    public T visit(FunctionDefinitionList.DefinitionAndList definitionList);

    public T visit(Program program);

    public T visit(Program.Body program);

    public T visit(Program.BodyDefinitionList program);

}

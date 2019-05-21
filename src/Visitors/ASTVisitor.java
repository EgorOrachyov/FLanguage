package Visitors;

import Errors.LangException;
import Language.*;
import Parsing.ASTNode;

public interface ASTVisitor<T> {

    public T visit(ASTNode node) throws LangException;

    public T visit(Expression.Var expression) throws LangException;

    public T visit(Expression.Num expression) throws LangException;

    public T visit(Expression.Binary expression) throws LangException;

    public T visit(Expression.Call expression) throws LangException;

    public T visit(Expression.If expression) throws LangException;

    public T visit(BinaryExpression expression) throws LangException;

    public T visit(ArgumentsList.Argument arguments) throws LangException;

    public T visit(ArgumentsList.ArgumentAndList arguments) throws LangException;

    public T visit(IfExpression expression) throws LangException;

    public T visit(CallExpression call) throws LangException;

    public T visit(ParamsList.OneParam params) throws LangException;

    public T visit(ParamsList.OneParamAndList params) throws LangException;

    public T visit(FunctionDefinition function) throws LangException;

    public T visit(FunctionDefinitionList.Definition definitionList) throws LangException;

    public T visit(FunctionDefinitionList.DefinitionAndList definitionList) throws LangException;

    public T visit(Program.Body program) throws LangException;

    public T visit(Program.BodyDefinitionList program) throws LangException;

}

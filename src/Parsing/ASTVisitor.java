package Parsing;

import Errors.ASTException;
import Language.*;

public interface ASTVisitor<T> {

    public T visit(ASTNode node) throws ASTException;

    public T visit(Expression.Var expression) throws ASTException;

    public T visit(Expression.Num expression) throws ASTException;

    public T visit(Expression.Binary expression) throws ASTException;

    public T visit(Expression.Call expression) throws ASTException;

    public T visit(Expression.If expression) throws ASTException;

    public T visit(BinaryExpression expression) throws ASTException;

    public T visit(ArgumentsList.Argument arguments) throws ASTException;

    public T visit(ArgumentsList.ArgumentAndList arguments) throws ASTException;

    public T visit(IfExpression expression) throws ASTException;

    public T visit(CallExpression call) throws ASTException;

    public T visit(ParamsList.OneParam params) throws ASTException;

    public T visit(ParamsList.OneParamAndList params) throws ASTException;

    public T visit(FunctionDefinition function) throws ASTException;

    public T visit(FunctionDefinitionList.Definition definitionList) throws ASTException;

    public T visit(FunctionDefinitionList.DefinitionAndList definitionList) throws ASTException;

    public T visit(Program.Body program) throws ASTException;

    public T visit(Program.BodyDefinitionList program) throws ASTException;

}

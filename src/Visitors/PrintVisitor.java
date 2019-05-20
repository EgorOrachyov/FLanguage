package Visitors;

import Language.*;
import Lexing.Token;
import Parsing.ASTNode;

public class PrintVisitor implements ASTVisitor<String> {

    @Override
    public String visit(ASTNode node) {
        return node.accept(this);
    }

    @Override
    public String visit(Expression.If expression) {
        return expression.statement.accept(this);
    }

    @Override
    public String visit(Expression.Num expression) {
        return expression.token.getLexeme();
    }

    @Override
    public String visit(Expression.Var expression) {
        return expression.token.getLexeme();
    }

    @Override
    public String visit(Expression.Call expression) {
        return expression.statement.accept(this);
    }

    @Override
    public String visit(Expression.Binary expression) {
        return expression.statement.accept(this);
    }

    @Override
    public String visit(Language.BinaryExpression expression) {
        return "(" + expression.expr1.accept(this) + operator(expression.operator) + expression.expr2.accept(this) + ")";
    }

    @Override
    public String visit(ArgumentsList.Argument arguments) {
        return arguments.arg.accept(this);
    }

    @Override
    public String visit(ArgumentsList.ArgumentAndList arguments) {
        return arguments.arg.accept(this) + "," + arguments.list.accept(this);
    }

    @Override
    public String visit(CallExpression call) {
        return call.name.getLexeme() + '(' + call.arguments.accept(this) + ")";
    }

    @Override
    public String visit(IfExpression expression) {
        return '[' + expression.condition.accept(this) + "]?{" +
                expression.ifTrue.accept(this)    + "}:{" +
                expression.ifFalse.accept(this)   + "}";
    }

    @Override
    public String visit(ParamsList.OneParam params) {
        return params.param.getLexeme();
    }

    @Override
    public String visit(ParamsList.OneParamAndList params) {
        return params.param.getLexeme() + "," + params.list.accept(this);
    }

    @Override
    public String visit(FunctionDefinition function) {
        return function.name.getLexeme() + "(" +
               function.paramsList.accept(this) + ")={" +
               function.funBody.accept(this) + "}";
    }

    @Override
    public String visit(FunctionDefinitionList.Definition definitionList) {
        return definitionList.funDef.accept(this) + "\n";
    }

    @Override
    public String visit(FunctionDefinitionList.DefinitionAndList definitionList) {
        return definitionList.funDef.accept(this) + "\n" + definitionList.funDefList.accept(this);
    }

    @Override
    public String visit(Program.Body program) {
        return program.expression.accept(this);
    }

    @Override
    public String visit(Program.BodyDefinitionList program) {
        return program.definitions.accept(this) + program.expression.accept(this);
    }

    private String operator(Token token) {
        switch (token.getType()) {
            case PLUS: return "+";
            case MINUS: return "-";
            case STAR: return "*";
            case SLASH: return "/";
            case PERCENT: return "%";
            case GREATER: return ">";
            case LESS: return "<";
            case EQUAL: return "=";
            default: return "";
         }
    }

}

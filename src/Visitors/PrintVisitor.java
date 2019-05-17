package Visitors;

import Language.*;
import Parsing.ASTVisitor;

public class PrintVisitor implements ASTVisitor<String> {

    @Override
    public String visit(Language.Expression expression) {
        if (expression.type == Expression.Type.IDENTIFIER ||
            expression.type == Expression.Type.NUMBER) {
            return expression.token.getLexeme();
        } else {
            return expression.expression.accept(this);
        }
    }

    @Override
    public String visit(Language.BinaryExpression expression) {
        return expression.expr1.accept(this) + '<' + expression.operator.getType() + '>' + expression.expr2.accept(this);
    }

    @Override
    public String visit(ArgumentsList arguments) {
        if (arguments.type == ArgumentsList.Type.EXPRESSION) {
            return arguments.expression.accept(this);
        } else {
            return arguments.expression.accept(this) + "," + arguments.list.accept(this);
        }
    }

    @Override
    public String visit(CallExpression call) {
        return call.token.getLexeme() + '(' + call.list.accept(this) + ")";
    }

    @Override
    public String visit(IfExpression expression) {
        return '[' + expression.condition.accept(this) + "]?(" +
                expression.ifTrue.accept(this)    + "):(" +
                expression.ifFalse.accept(this)   + ")";
    }

    @Override
    public String visit(ParamsList params) {
        if (params.type == ParamsList.Type.IDENTIFIER) {
            return params.token.getLexeme();
        } else {
            return params.token.getLexeme() + "," + params.list.accept(this);
        }
    }

    @Override
    public String visit(FunctionDefinition function) {
        return function.identifier.getLexeme() + "(" +
               function.paramsList.accept(this) + ")={" +
               function.funBody.accept(this) + "}";
    }

    @Override
    public String visit(FunctionDefinitionList definitionList) {
        if (definitionList.type == FunctionDefinitionList.Type.EMPTY) {
            return "";
        } else if (definitionList.type == FunctionDefinitionList.Type.ONE) {
            return definitionList.function.accept(this) + "\n";
        } else {
            return definitionList.function.accept(this) + "\n" + definitionList.list.accept(this);
        }
    }

    @Override
    public String visit(Program entry) {
        return entry.definitions.accept(this) + entry.expression.accept(this);
    }

}
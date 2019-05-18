package Visitors;

import Language.*;
import Lexing.Token;
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
        return "(" + expression.expr1.accept(this) + operator(expression.operator) + expression.expr2.accept(this) + ")";
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
        return '[' + expression.condition.accept(this) + "]?{" +
                expression.ifTrue.accept(this)    + "}:{" +
                expression.ifFalse.accept(this)   + "}";
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
        if (definitionList.type == FunctionDefinitionList.Type.ONE) {
            return definitionList.function.accept(this) + "\n";
        } else {
            return definitionList.function.accept(this) + "\n" + definitionList.list.accept(this);
        }
    }

    @Override
    public String visit(Program entry) {
        if (entry.type.equals(Program.Type.EXPRESSION)) {
            return entry.expression.accept(this);
        } else {
            return entry.definitions.accept(this) + entry.expression.accept(this);
        }
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

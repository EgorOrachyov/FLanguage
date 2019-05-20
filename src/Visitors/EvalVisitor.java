package Visitors;

import Errors.ErrorMessage;
import Errors.InterpreterException;
import Language.*;
import Parsing.ASTNode;
import Runtime.FunctionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitor implements ASTVisitor<Integer> {

    private Map<String, Integer> scope;
    private List<Integer> callArguments;
    private final Map<String, FunctionInfo> functions;

    public EvalVisitor(Map<String, FunctionInfo> functions) {
        this.functions = functions;
        this.callArguments = new ArrayList<>();
        this.scope = new HashMap<>();
    }

    @Override
    public  Integer visit(ASTNode node) {
        return node.accept(this);
    }

    @Override
    public  Integer visit(Expression.Var expression) {
        Integer value = scope.get(expression.name);
        if (value != null) {
            return value;
        } else {
            throw new InterpreterException(ErrorMessage.PARAMETER_NOT_FOUND, expression.token);
        }
    }

    @Override
    public  Integer visit(Expression.Num expression) {
        return expression.num;
    }

    @Override
    public  Integer visit(Expression.Binary expression) {
        return expression.statement.accept(this);
    }

    @Override
    public  Integer visit(Expression.Call expression) {
        return expression.statement.accept(this);
    }

    @Override
    public  Integer visit(Expression.If expression) {
        return expression.statement.accept(this);
    }

    @Override
    public  Integer visit(BinaryExpression expression) {
        final int expr1 = expression.expr1.accept(this);
        final int expr2 = expression.expr2.accept(this);

        switch (expression.operator.getType()) {

            case PLUS:
                return expr1 + expr2;
            case MINUS:
                return expr1 - expr2;
            case STAR:
                return expr1 * expr2;
            case SLASH:
                if (expr2 == 0) {
                    throw new InterpreterException(ErrorMessage.RUNTIME_ERROR,
                            (new PrintVisitor().visit(expression)),
                            expression.line());
                } else {
                    return expr1 / expr2;
                }
            case PERCENT:
                if (expr2 == 0) {
                    throw new InterpreterException(ErrorMessage.RUNTIME_ERROR,
                            (new PrintVisitor().visit(expression)),
                            expression.line());
                } else {
                    return expr1 % expr2;
                }
            case GREATER:
                return (expr1 > expr2 ? 1 : 0);
            case LESS:
                return (expr1 < expr2 ? 1 : 0);
            case EQUAL:
                return (expr1 == expr2 ? 1 : 0);

            default:
                throw new InterpreterException(ErrorMessage.RUNTIME_ERROR,
                        (new PrintVisitor().visit(expression)),
                        expression.line());
        }
    }

    @Override
    public  Integer visit(ArgumentsList.Argument arguments) {
        final int value = arguments.arg.accept(this);
        callArguments.add(value);
        return null;
    }

    @Override
    public  Integer visit(ArgumentsList.ArgumentAndList arguments) {
        final int value = arguments.arg.accept(this);
        callArguments.add(value);
        arguments.list.accept(this);
        return null;
    }

    @Override
    public  Integer visit(IfExpression expression) {
        final int condition = expression.condition.accept(this);
        if (condition != 0) {
            return expression.ifTrue.accept(this);
        } else {
            return expression.ifFalse.accept(this);
        }
    }

    @Override
    public Integer visit(CallExpression call) {
        FunctionInfo function = functions.get(call.name.getLexeme());
        if (function != null) {
            // Save the scope before enter new function evaluation
            Map<String, Integer> oldScope = scope;
            List<Integer> oldCallArguments = callArguments;

            // Prepare buffer to collect argument of function call
            callArguments = new ArrayList<>();

            // Associate call arguments with function params
            call.arguments.accept(this);

            // Create new scope and setup all the function params with default values
            if (callArguments.size() == function.getParamsCount()) {
                scope = new HashMap<>();
                for (int i = 0; i < callArguments.size(); i++) {
                    scope.put(function.getParams().get(i), callArguments.get(i));
                }
            } else {
                throw new InterpreterException(ErrorMessage.ARGUMENT_NUMBER_MISMATCH, call.name);
            }

            // Eval function body
            final int result = function.getFunctionBody().accept(this);

            // Return scope of current expression evaluation frame
            scope = oldScope;
            callArguments = oldCallArguments;

            return result;
        } else {
            throw new InterpreterException(ErrorMessage.FUNCTION_NOT_FOUND, call.name);
        }
    }

    @Override
    public  Integer visit(ParamsList.OneParam params) {
        return null;
    }

    @Override
    public  Integer visit(ParamsList.OneParamAndList params) {
        return null;
    }

    @Override
    public  Integer visit(FunctionDefinition function) {
        return null;
    }

    @Override
    public  Integer visit(FunctionDefinitionList.Definition definitionList) {
        return null;
    }

    @Override
    public  Integer visit(FunctionDefinitionList.DefinitionAndList definitionList) {
        return null;
    }

    @Override
    public  Integer visit(Program.Body program) {
        return program.expression.accept(this);
    }

    @Override
    public  Integer visit(Program.BodyDefinitionList program) {
        return program.expression.accept(this);
    }
}

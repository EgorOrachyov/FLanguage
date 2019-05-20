package Visitors;

import Errors.ErrorMessage;
import Errors.FunctionInfoException;
import Language.*;
import Parsing.ASTNode;
import Parsing.ASTVisitor;
import Runtime.FunctionInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionsInfoVisitor implements ASTVisitor<FunctionInfoException> {

    private HashMap<String, FunctionInfo> functionInfo = new HashMap<>();
    private FunctionDefinition currentFunction;
    private ArrayList<String> currentParams;

    public HashMap<String, FunctionInfo> getFunctionInfo() {
        return functionInfo;
    }

    @Override
    public FunctionInfoException visit(ASTNode node) {
        return node.accept(this);
    }

    @Override
    public FunctionInfoException visit(Expression.Var expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(Expression.Num expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(Expression.Binary expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(Expression.Call expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(Expression.If expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(BinaryExpression expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(ArgumentsList.Argument arguments) {
        return null;
    }

    @Override
    public FunctionInfoException visit(ArgumentsList.ArgumentAndList arguments) {
        return null;
    }

    @Override
    public FunctionInfoException visit(IfExpression expression) {
        return null;
    }

    @Override
    public FunctionInfoException visit(CallExpression call) {
        return null;
    }

    @Override
    public FunctionInfoException visit(ParamsList.OneParam params) {
        if (currentParams.contains(params.param.getLexeme())) {
            return new FunctionInfoException(ErrorMessage.PARAM_REDEFINITION, currentFunction);
        } else {
            currentParams.add(params.param.getLexeme());
            return null;
        }
    }

    @Override
    public FunctionInfoException visit(ParamsList.OneParamAndList params) {
        if (currentParams.contains(params.param.getLexeme())) {
            return new FunctionInfoException(ErrorMessage.PARAM_REDEFINITION, currentFunction);
        } else {
            currentParams.add(params.param.getLexeme());
            return params.list.accept(this);
        }
    }

    @Override
    public FunctionInfoException visit(FunctionDefinition function) {
        if (functionInfo.containsKey(function.name.getLexeme())) {
            return new FunctionInfoException(ErrorMessage.FUNCTION_REDEFINITION, function);
        } else {
            currentFunction = function;
            currentParams = new ArrayList<>();
            FunctionInfoException exception = function.paramsList.accept(this);
            if (exception == null) {
                functionInfo.put(function.name.getLexeme(), new FunctionInfo(function, currentParams));
                return null;
            } else {
                return exception;
            }
        }
    }

    @Override
    public FunctionInfoException visit(FunctionDefinitionList.Definition definitionList) {
        return definitionList.funDef.accept(this);
    }

    @Override
    public FunctionInfoException visit(FunctionDefinitionList.DefinitionAndList definitionList) {
        FunctionInfoException exception = definitionList.funDef.accept(this);
        if (exception == null) {
            return definitionList.funDefList.accept(this);
        } else {
            return exception;
        }
    }

    @Override
    public FunctionInfoException visit(Program.Body program) {
        return null;
    }

    @Override
    public FunctionInfoException visit(Program.BodyDefinitionList program) {
        return program.definitions.accept(this);
    }
}

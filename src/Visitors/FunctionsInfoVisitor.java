package Visitors;

import Errors.ErrorMessage;
import Errors.FunctionInfoException;
import Language.*;
import Parsing.ASTNode;
import Runtime.FunctionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FunctionsInfoVisitor implements ASTVisitor<Integer> {

    private Map<String, FunctionInfo> functionInfo = new HashMap<>();
    private FunctionDefinition function;
    private ArrayList<String> params;

    public Map<String, FunctionInfo> getFunctionInfo() {
        return functionInfo;
    }

    @Override
    public Integer visit(ASTNode node) {
        return node.accept(this);
    }

    @Override
    public Integer visit(Expression.Var expression) {
        return null;
    }

    @Override
    public Integer visit(Expression.Num expression) {
        return null;
    }

    @Override
    public Integer visit(Expression.Binary expression) {
        return null;
    }

    @Override
    public Integer visit(Expression.Call expression) {
        return null;
    }

    @Override
    public Integer visit(Expression.If expression) {
        return null;
    }

    @Override
    public Integer visit(BinaryExpression expression) {
        return null;
    }

    @Override
    public Integer visit(ArgumentsList.Argument arguments) {
        return null;
    }

    @Override
    public Integer visit(ArgumentsList.ArgumentAndList arguments) {
        return null;
    }

    @Override
    public Integer visit(IfExpression expression) {
        return null;
    }

    @Override
    public Integer visit(CallExpression call) {
        return null;
    }

    @Override
    public Integer visit(ParamsList.OneParam params) {
        if (this.params.contains(params.param.getLexeme())) {
            throw new FunctionInfoException(ErrorMessage.PARAM_REDEFINITION, function);
        } else {
            this.params.add(params.param.getLexeme());
        }

        return null;
    }

    @Override
    public Integer visit(ParamsList.OneParamAndList params) {
        if (this.params.contains(params.param.getLexeme())) {
            throw new FunctionInfoException(ErrorMessage.PARAM_REDEFINITION, function);
        } else {
            this.params.add(params.param.getLexeme());
            params.list.accept(this);
        }

        return null;
    }

    @Override
    public Integer visit(FunctionDefinition function) {
        if (functionInfo.containsKey(function.name.getLexeme())) {
            throw new FunctionInfoException(ErrorMessage.FUNCTION_REDEFINITION, function);
        } else {
            this.function = function;
            params = new ArrayList<>();
            function.paramsList.accept(this);
            functionInfo.put(function.name.getLexeme(), new FunctionInfo(function, params));
        }

        return null;
    }

    @Override
    public Integer visit(FunctionDefinitionList.Definition definitionList) {
        return definitionList.funDef.accept(this);
    }

    @Override
    public Integer visit(FunctionDefinitionList.DefinitionAndList definitionList) {
        definitionList.funDef.accept(this);
        definitionList.funDefList.accept(this);
        return null;
    }

    @Override
    public Integer visit(Program.Body program) {
        return null;
    }

    @Override
    public Integer visit(Program.BodyDefinitionList program) {
        return program.definitions.accept(this);
    }
}

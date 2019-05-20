package Runtime;

import Language.Expression;
import Language.FunctionDefinition;
import java.util.ArrayList;

public class FunctionInfo {

    private FunctionDefinition definition;
    private ArrayList<String> params;

    public FunctionInfo(FunctionDefinition definition, ArrayList<String> params) {
        this.definition = definition;
        this.params = params;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public int getParamsCount() {
        return params.size();
    }

    public FunctionDefinition getDefinition() {
        return definition;
    }

    public String getFunctionName() {
        return definition.name.getLexeme();
    }

    public Expression getFunctionBody() {
        return definition.funBody;
    }
}

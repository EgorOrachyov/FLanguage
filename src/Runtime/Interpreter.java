package Runtime;

import Errors.LangException;
import Language.Program;
import Lexing.Lexer;
import Parsing.Parser;
import Utils.Carriage;
import Visitors.EvalVisitor;
import Visitors.FunctionsInfoVisitor;

import java.util.Map;

public class Interpreter {

    private Integer result;
    private String source;
    private Program program;
    private Map<String, FunctionInfo> functions;

    public Interpreter(String source) {
        this.source = source;
    }

    public void prepare() throws LangException {
        Lexer lexer = new Lexer(new Carriage(source));
        lexer.run();
        Parser parser = new Parser(lexer.getTokens());
        parser.run();
        program = parser.getProgram();
        FunctionsInfoVisitor visitor = new FunctionsInfoVisitor();
        program.accept(visitor);
        functions = visitor.getFunctionInfo();
    }

    public void run() throws LangException {
        EvalVisitor visitor = new EvalVisitor(functions);
        result = program.accept(visitor);
    }

    public Integer getResult() {
        return result;
    }

}

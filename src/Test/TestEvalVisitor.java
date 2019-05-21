package Test;

import Errors.FunctionInfoException;
import Errors.InterpreterException;
import Errors.LexerException;
import Errors.ParserException;
import Language.Program;
import Lexing.Lexer;
import Parsing.Parser;
import Utils.Carriage;
import Visitors.EvalVisitor;
import Visitors.FunctionsInfoVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestEvalVisitor extends Assert {

    private final String input;
    private final String expected;

    public TestEvalVisitor(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[] input() {
        return new Object[][] {
                { "(2+-2)", "0"},
                { "(2+2)", "4" },
                { "(2+((3*4)/5))", "4" },
                { "[((10+20)>(20+10))]?{1}:{0}", "0" },
                { "g(x)={(f(x)+f((x/2)))}\nf(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}\ng(10)", "60" },
                { "f(x)={[(x>2)]?{(f((x-1))+f((x-2)))}:{1}}\nf(10)", "55" },
                { "f(x)={y}\nf(10)", "PARAMETER NOT FOUND y:1" },
                { "g(x)={f(x)}\ng(10)", "FUNCTION NOT FOUND f:1" },
                { "g(x)={(x+1)}\ng(10,20)", "ARGUMENT NUMBER MISMATCH g:2" },
                { "g(a,b)={(a/b)}\ng(10,0)", "RUNTIME ERROR (a/b):1" },
        };
    }

    @Test
    public void test() {
        Lexer lexer = new Lexer(new Carriage(input));

        try {
            lexer.run();
        } catch (LexerException e) {
            assertEquals(expected, e.getMessage());
            return;
        }

        Parser parser = new Parser(lexer.getTokens());

        try {
            parser.run();
        } catch (ParserException e) {
            assertEquals(expected, e.getMessage());
            return;
        }

        Program program = parser.getProgram();
        FunctionsInfoVisitor functionsInfoVisitor = new FunctionsInfoVisitor();

        try {
            functionsInfoVisitor.visit(program);
        } catch (FunctionInfoException e) {
            assertEquals(expected, e.getMessage());
            return;
        }

        EvalVisitor evalVisitor = new EvalVisitor(functionsInfoVisitor.getFunctionInfo());
        Integer result = 0;

        try {
            result = evalVisitor.visit(program);
        } catch (InterpreterException e) {
            assertEquals(expected, e.getMessage());
            return;
        }

        assertEquals(expected, result.toString());
    }

    @Test
    public void testWithPrinting() {
        System.out.print("\n" + input + "\n\n");

        Lexer lexer = new Lexer(new Carriage(input));

        try {
            lexer.run();
        } catch (LexerException e) {
            assertEquals(expected, e.getMessage());
            System.out.println(e.getMessage());
            return;
        }

        Parser parser = new Parser(lexer.getTokens());

        try {
            parser.run();
        } catch (ParserException e) {
            assertEquals(expected, e.getMessage());
            System.out.println(e.getMessage());
            return;
        }

        Program program = parser.getProgram();
        FunctionsInfoVisitor functionsInfoVisitor = new FunctionsInfoVisitor();

        try {
            functionsInfoVisitor.visit(program);
        } catch (FunctionInfoException e) {
            assertEquals(expected, e.getMessage());
            System.out.println(e.getMessage());
            return;
        }

        EvalVisitor evalVisitor = new EvalVisitor(functionsInfoVisitor.getFunctionInfo());
        Integer result = 0;

        try {
            result = evalVisitor.visit(program);
        } catch (InterpreterException e) {
            assertEquals(expected, e.getMessage());
            System.out.println(e.getMessage());
            return;
        }

        assertEquals(expected, result.toString());
        System.out.println("Output: " + result);
    }

}

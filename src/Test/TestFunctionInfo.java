package Test;

import Errors.FunctionInfoException;
import Errors.LexerException;
import Errors.ParserException;
import Language.Program;

import Lexing.Lexer;
import Lexing.Token;

import Parsing.Parser;

import Runtime.FunctionInfo;

import Utils.Carriage;
import Utils.CharacterArray;

import Visitors.FunctionsInfoVisitor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Parameterized.class)
public class TestFunctionInfo extends Assert {

    private final String expected;

    public TestFunctionInfo(String code) {
        this.expected = code;
    }

    @Parameterized.Parameters
    public static Object[] input() {
        return new Object[] {
                "f(x)={(x+x)}\nf(-10)",
                "g(x)={(f(x)+f((x/2)))}\n" +
                "f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}\n" +
                "g(10)",
                "((-10+190)%13)",
                "s(x)={(x*x)}\n" +
                "f(x,y,z)={((s(x)+s(y))+s(z))}\n" +
                "[(f(-1,0,1)>f(2,0,-1))]?{1}:{-1}",
                "f(x,x)={(x+x)}\n" +
                "f(10,20)",
                "g(x)={x}\n" +
                "g(x,y)={(x*y)}\n" +
                "(2+2)",
                "s(x)={(x*x)}\n" +
                "f(x,y,z)={((s(x)+s(y))+s(z))}\n" +
                "g(x,y,z)={((x+y)+z)}\n" +
                "k(x,y,z,w)={(((x*y)*z)*w)}\n" +
                "f(1,g(2,3,4),k(5,6,7,8))"
        };
    }

    @Test
    public void testFunctionInfoCollect() {

        Lexer lexer = new Lexer(new Carriage<>(CharacterArray.convert(expected)));

        try {
            lexer.run();
        } catch (LexerException e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }

        List<Token> tokens = lexer.getTokens();

        Parser parser = new Parser(tokens);

        try {
            parser.run();
        } catch (ParserException e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }

        Program program = parser.getProgram();
        FunctionsInfoVisitor visitor = new FunctionsInfoVisitor();

        FunctionInfoException exception = program.accept(visitor);
        if (exception != null) {
            System.out.println(exception.getMessage());
            assertTrue(true);
        } else {
            HashMap<String, FunctionInfo> info = visitor.getFunctionInfo();
            for (Map.Entry<String, FunctionInfo> fun : info.entrySet()) {
                System.out.println(fun.getKey() + " " + fun.getValue().getParams());
            }
        }
    }

}
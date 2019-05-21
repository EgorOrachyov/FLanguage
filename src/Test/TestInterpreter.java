package Test;

import Errors.*;
import Runtime.Interpreter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestInterpreter extends Assert {

    private final String input;
    private final String expected;

    public TestInterpreter(String input, String expected) {
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
                { "(a+ b)", "SYNTAX ERROR" },
                { "1 + 2 + 3 + 4 + 5", "SYNTAX ERROR" },
                { "f(x)={y}\nf(10)", "PARAMETER NOT FOUND y:1" },
                { "g(x)={f(x)}\ng(10)", "FUNCTION NOT FOUND f:1" },
                { "g(x)={(x+1)}\ng(10,20)", "ARGUMENT NUMBER MISMATCH g:2" },
                { "g(a,b)={(a/b)}\ng(10,0)", "RUNTIME ERROR (a/b):1" },
                { "f(x)={(x+x)}\nf(x,y)={(x+y)}\nf(10)", "FUNCTION REDEFINITION f(x,y)={(x+y)}:2" },
                { "f(x,x)={(x*x)}\n(10*13)", "PARAM REDEFINITION f(x,x)={(x*x)}:1" },
        };
    }

    @Test
    public void test() {
        try {
            Interpreter interpreter = new Interpreter(input);
            interpreter.prepare();
            interpreter.run();
            Integer result = interpreter.getResult();
            assertEquals(expected, result.toString());
        } catch (LangException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testWithPrinting() {
        System.out.print("\n" + input + "\n\n");
        try {
            Interpreter interpreter = new Interpreter(input);
            interpreter.prepare();
            interpreter.run();
            Integer result = interpreter.getResult();
            assertEquals(expected, result.toString());
            System.out.println("Output: " + result);
        } catch (LangException e) {
            assertEquals(expected, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}

package Test;

import Lexing.Lexer;
import Lexing.Carriage;
import Errors.InterpreterException;
import Lexing.Token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLexer {

    private final String source;

    public TestLexer(String source) {
        this.source = source;
    }

    @Parameterized.Parameters
    public static Object[] sources() {
        return new Object[] {
                "(+)(-)%%%=,\n([[[[]]]]){}\n%--",
                "\n\n\n\n\n\n\n",
                "*()%)( ",
                "16*4+(a-b)/2+(189%13)",
                "g(x)={(f(x)+f((x/2)))}\n" +
                "f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}\n" +
                "g(10)",
                "__ew11\n1__w2,[123\n21]:{ddd},\n{dsss]"
        };
    }

    @Test
    public void test() {
        Carriage carriage = new Carriage(source);
        Lexer lexer = new Lexer(carriage);

        try {
            lexer.run();
        } catch (InterpreterException e) {
            System.out.println(e.getMessage());
            return;
        }

        for (Token t : lexer.getTokens()) {
            System.out.println(t.toString());
        }
    }

}

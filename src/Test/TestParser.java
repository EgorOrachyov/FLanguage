package Test;

import Errors.LexerException;
import Errors.ParserException;

import Language.Program;

import Lexing.Lexer;
import Lexing.Token;

import Parsing.Parser;

import Utils.Carriage;
import Utils.CharacterArray;

import Visitors.PrintVisitor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class TestParser extends Assert {

    private final String expected;

    public TestParser(String code) {
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
                "[(f(-1,0,1)>f(2,0,-1))]?{1}:{-1}"
        };
    }

    @Test
    public void testFirstParse() {

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
        PrintVisitor visitor = new PrintVisitor();

        String actual = program.accept(visitor);
        System.out.println(actual);

        assertEquals(expected, actual);
    }

}

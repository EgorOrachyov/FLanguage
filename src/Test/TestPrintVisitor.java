package Test;

import Language.*;
import Lexing.Token;
import Lexing.TokenType;
import Visitors.PrintVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestPrintVisitor extends Assert {

    private final PrintVisitor visitor = new PrintVisitor();
    private final Program program;
    private final String expected;

    public TestPrintVisitor(Program p, String e) {
        program = p;
        expected = e;
    }

    @Parameterized.Parameters
    public static Object[] programs() {
        return new Object[][] {
                {
                        new Program(
                                new FunctionDefinitionList(
                                        new FunctionDefinition(
                                                new Token(TokenType.IDENTIFIER, "f", 0),
                                                new ParamsList(
                                                        new Token(TokenType.IDENTIFIER, "x", 0)
                                                ),
                                                new Expression(
                                                        new BinaryExpression(
                                                                new Expression(
                                                                        new Token(TokenType.IDENTIFIER, "x", 0)
                                                                ),
                                                                new Token(TokenType.STAR, "*", 0),
                                                                new Expression(
                                                                        new Token(TokenType.IDENTIFIER, "x", 0)
                                                                )
                                                        )
                                                )
                                        ),
                                        new FunctionDefinitionList(
                                                new FunctionDefinition(
                                                        new Token(TokenType.IDENTIFIER, "g", 1),
                                                        new ParamsList(
                                                                new Token(TokenType.IDENTIFIER, "x", 1)
                                                        ),
                                                        new Expression(
                                                                new BinaryExpression(
                                                                        new Expression(
                                                                                new Token(TokenType.IDENTIFIER, "x", 1)
                                                                        ),
                                                                        new Token(TokenType.PLUS, "+", 1),
                                                                        new Expression(
                                                                                new Token(TokenType.IDENTIFIER, "x", 1)
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                ),
                                new Expression(
                                        new CallExpression(
                                                new Token(TokenType.IDENTIFIER, "g", 2),
                                                new ArgumentsList(
                                                        new Expression(
                                                                new Token(TokenType.NUMBER, "10", 2)
                                                        )
                                                )
                                        )
                                )
                        )
                    ,
                        "f(x)={x<STAR>x}\ng(x)={x<PLUS>x}\ng(10)"
                }
        };
    }

    @Test
    public void test() {
        String actual = visitor.visit(program);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

}

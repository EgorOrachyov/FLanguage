package Language;

import Lexing.Token;
import Parsing.ASTNode;
import Visitors.ASTVisitor;

public abstract class Expression extends ASTNode {

    public static class Var extends Expression {

        public final Token token;
        public final String name;

        public Var(Token token) {
            this.token = token;
            this.name = token.getLexeme();
        }

        @Override
        public int line() {
            return token.getLine();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class Num extends Expression {

        public final Token token;
        public final int num;

        public Num(Token token) {
            this.token = token;
            this.num = Integer.valueOf(token.getLexeme());
        }

        @Override
        public int line() {
            return token.getLine();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class Binary extends Expression {

        public final BinaryExpression statement;

        public Binary(BinaryExpression expression) {
            this.statement = expression;
        }

        @Override
        public int line() {
            return statement.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class Call extends Expression {

        public final CallExpression statement;

        public Call(CallExpression expression) {
            this.statement = expression;
        }

        @Override
        public int line() {
            return statement.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class If extends Expression {

        public final IfExpression statement;

        public If(IfExpression expression) {
            this.statement = expression;
        }

        @Override
        public int line() {
            return statement.line();
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

}
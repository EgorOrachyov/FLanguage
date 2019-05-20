package Parsing;

public abstract class ASTNode {

    public abstract int line();

    public abstract <T> T accept(ASTVisitor<T> visitor);

}

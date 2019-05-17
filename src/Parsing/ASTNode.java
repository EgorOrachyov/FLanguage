package Parsing;

public abstract class ASTNode {

    private final ASTNodeType type;

    public ASTNode(ASTNodeType type) {
        this.type = type;
    }

    public ASTNodeType getType() {
        return type;
    }

    public abstract <T> T accept(ASTVisitor<T> visitor);

}

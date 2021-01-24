package tokenizer;

import visitor.TokenVisitor;

public class OperationToken implements Token {
    private char operation;

    public OperationToken(char operation) {
        this.operation = operation;
    }

    public char getOperation() {
        return operation;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}

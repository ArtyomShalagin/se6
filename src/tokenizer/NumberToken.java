package tokenizer;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private int number;

    public NumberToken(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}

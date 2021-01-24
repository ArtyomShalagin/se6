package tokenizer;

import visitor.TokenVisitor;

public class BracketToken implements Token {
    private char bracket;

    public BracketToken(char bracket) {
        this.bracket = bracket;
    }

    public char getBracket() {
        return bracket;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

}

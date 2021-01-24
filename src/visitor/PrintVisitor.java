package visitor;

import tokenizer.BracketToken;
import tokenizer.NumberToken;
import tokenizer.OperationToken;
import tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private List<String> output = new ArrayList<>();

    public void print(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        System.out.println(String.join(" ", output));
    }

    @Override
    public void visit(NumberToken token) {
        output.add("NUMBER(" + token.getNumber() + ")");
    }

    @Override
    public void visit(BracketToken token) {
        switch ((token.getBracket())) {
            case '(' -> output.add("LEFT");
            case ')' -> output.add("RIGHT");
        }
    }

    @Override
    public void visit(OperationToken token) {
        switch (token.getOperation()) {
            case '+' -> output.add("PLUS");
            case '-' -> output.add("MINUS");
            case '*' -> output.add("MULT");
            case '/' -> output.add("DIV");
            default -> throw new RuntimeException("Incorrect operation: " + token.getOperation());
        }
    }
}

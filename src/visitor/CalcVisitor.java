package visitor;

import tokenizer.BracketToken;
import tokenizer.NumberToken;
import tokenizer.OperationToken;
import tokenizer.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private Stack<Token> stack = new Stack<>();

    public int calculate(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        return ((NumberToken) stack.peek()).getNumber();
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(token);
    }

    @Override
    public void visit(BracketToken token) { }

    @Override
    public void visit(OperationToken token) {
        NumberToken x = (NumberToken) stack.pop();
        NumberToken y = (NumberToken) stack.pop();
        char c = token.getOperation();
        switch (c) {
            case '+' -> stack.push(new NumberToken(x.getNumber() + y.getNumber()));
            case '-' -> stack.push(new NumberToken(y.getNumber() - x.getNumber()));
            case '*' -> stack.push(new NumberToken(x.getNumber() * y.getNumber()));
            case '/' -> stack.push(new NumberToken(y.getNumber() / x.getNumber()));
        }
    }
}

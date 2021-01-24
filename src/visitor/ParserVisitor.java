package visitor;

import tokenizer.BracketToken;
import tokenizer.NumberToken;
import tokenizer.OperationToken;
import tokenizer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private List<Token> rpn = new ArrayList<>();
    private Stack<Token> stack = new Stack<>();

    private int getPriority(OperationToken token) {
        int p = -1;
        switch (token.getOperation()) {
            case '*', '/' -> p = 1;
            case '+', '-' -> p = 0;
        }
        return p;
    }

    public List<Token> toRPN(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        while (!stack.isEmpty()) {
            Token token = stack.pop();
            if (token instanceof BracketToken) {
                throw new RuntimeException("Unexpected bracket");
            }
            rpn.add(token);
        }
        return rpn;
    }

    @Override
    public void visit(NumberToken token) {
        rpn.add(token);
    }

    @Override
    public void visit(BracketToken token) {
        if (token.getBracket() == '(') {
            stack.push(token);
        } else {
            while (true) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Invalid expression");
                }
                Token next = stack.pop();
                if (next instanceof BracketToken) {
                    break;
                } else {
                    rpn.add(next);
                }
            }
        }
    }

    @Override
    public void visit(OperationToken token) {
        while (!stack.isEmpty()) {
            Token op = stack.peek();
            if (!(op instanceof OperationToken)) {
                break;
            }
            if (getPriority((OperationToken) op) >= getPriority(token)) {
                rpn.add(op);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }
}

package tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String expression) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int number = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + expression.charAt(i) - '0';
                    i++;
                }
                i--;
                tokens.add(new NumberToken(number));
            } else if (c == '(' || c == ')') {
                tokens.add(new BracketToken(c));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                tokens.add(new OperationToken(c));
            } else if (c != ' ') {
                throw new RuntimeException("Unexpected char: " + c);
            }
        }
        return tokens;
    }
}

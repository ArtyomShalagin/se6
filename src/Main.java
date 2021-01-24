import tokenizer.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression = reader.readLine();

        List<Token> tokens = new Tokenizer().tokenize(expression);
        new PrintVisitor().print(tokens);
        List<Token> rpn = new ParserVisitor().toRPN(tokens);
        new PrintVisitor().print(rpn);

        System.out.println(new CalcVisitor().calculate(rpn));
    }
}

package Calculator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Calc {
    public static void main(String[] args) throws IOException {
        String str = """
                193
                a = 5
                b = 6
                a+b*2
                (1+2)*3
                (1+(1+5))*4
                clear
                a = 5
                a = 4
                a+b*2
                """;
        CharStream input = CharStreams.fromStream(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));

        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog(); // parse
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);

    }
}

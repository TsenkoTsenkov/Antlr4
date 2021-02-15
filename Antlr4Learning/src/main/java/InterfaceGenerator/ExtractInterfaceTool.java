package InterfaceGenerator;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExtractInterfaceTool {
    public static void main(String[] args) {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;

        try {
            if ( inputFile!=null ) {
                is = new FileInputStream(inputFile);
            }

            CharStream input = CharStreams.fromStream(is);
            JavaLexer lexer = new JavaLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);
            ParseTree tree = parser.compilationUnit(); // parse

            ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
            ExtractInterfaceListener extractor = new ExtractInterfaceListener(parser);
            walker.walk(extractor, tree); // initiate walk of tree with listener

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

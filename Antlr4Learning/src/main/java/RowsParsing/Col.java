package RowsParsing;
import org.antlr.v4.runtime.*;

import java.util.Scanner;

public class Col {
    public static void main(String[] args)  {
        try {
            Scanner scanner = new Scanner(System.in);

            String fileName = scanner.nextLine();
            int col = scanner.nextInt();

            CharStream input = CharStreams.fromFileName(fileName);
            RowsLexer lexer = new RowsLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RowsParser parser = new RowsParser(tokens, col); // pass column number!

            parser.setBuildParseTree(false); // don't waste time bulding a tree
            parser.file(); // parse
        } catch (Exception e) {
            System.out.println("Check the file name");
            e.printStackTrace();
        }
    }
}
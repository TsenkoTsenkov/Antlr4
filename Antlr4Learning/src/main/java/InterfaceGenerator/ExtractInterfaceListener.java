package InterfaceGenerator;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class ExtractInterfaceListener extends JavaBaseListener {
    JavaParser parser;
    public ExtractInterfaceListener(JavaParser parser) {this.parser = parser;}
    /** Listen to matches of classDeclaration */
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx){
        System.out.println();
        System.out.println("interface I"+ctx.Identifier()+" {");
    }
    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        System.out.print("package ");
        List<TerminalNode> nodes = ctx.qualifiedName().Identifier();
        for (int i=0; i<nodes.size(); i++) {
            System.out.print(nodes.get(i).getSymbol().getText());
            if (i < nodes.size()-1) {
                System.out.print(".");
            }
        }
    }

    @Override
    public void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        System.out.print(";");
        System.out.println();
    }

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        List<TerminalNode> identifier = ctx.qualifiedName().Identifier();
        String stringedId = identifier
                .stream()
                .map(node -> node.getSymbol().getText())
                .reduce("", (a,b) -> a + b + ".");

        System.out.println("import "+
                                    stringedId.substring(0, stringedId.length()-1)
                                     + ";"
        );
    }


    /** Listen to matches of methodDeclaration */
    @Override
    public void enterMethodDeclaration(
            JavaParser.MethodDeclarationContext ctx
    )
    {
// need parser to get tokens
        TokenStream tokens = parser.getTokenStream();
        String type = "void";
        if ( ctx.type()!=null ) {
            type = tokens.getText(ctx.type());
        }
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t"+type+" "+ctx.Identifier()+args+";");
    }

}

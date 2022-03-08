package fr.istic.vv;

import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/alexysguerin/fac/aoctp/src/main/java/impl/truc");
    

        SourceRoot root = new SourceRoot(file.toPath());
        //PublicElementsPrinter printer = new PublicElementsPrinter();
        // root.parse("", (localPath, absolutePath, result) -> {
        //     result.ifSuccessful(unit -> unit.accept(printer, null));
        //     return SourceRoot.Callback.Result.DONT_SAVE;
        // });
        PrivateElementsGetter printer = new PrivateElementsGetter();
        root.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> unit.accept(printer, null));
            return SourceRoot.Callback.Result.DONT_SAVE;
        });

    }


}

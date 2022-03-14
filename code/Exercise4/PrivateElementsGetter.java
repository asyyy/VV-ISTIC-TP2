package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.DeclaredType;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;


// This class visits a compilation unit and
// prints all public enum, classes or interfaces along with their public methods
public class PrivateElementsGetter extends VoidVisitorWithDefaults<Void> {

    public FileWriter file;

    public PrivateElementsGetter(FileWriter file){
        this.file = file;
    }

    public List<String> fieldsList = new ArrayList<>();
    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for(TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {
        if(!declaration.isPublic()) return;
        try {
            file.write("Package and Class : " + declaration.getFullyQualifiedName().orElse("[Anonymous]") + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(FieldDeclaration field : declaration.getFields()){
            field.accept(this, arg);
        }
        for(MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        
        //Printing nested types in the top level
        for(BodyDeclaration<?> member : declaration.getMembers()) {
            if (member instanceof TypeDeclaration)
                member.accept(this, arg);
        }
    }
    

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {  
        if(methodIsGetter(declaration.getNameAsString())){
            try {
                file.write("Getter : " + declaration.getNameAsString() + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean methodIsGetter(String methodName){
        for( String value : fieldsList){
            if(methodName.compareTo("get"+value) == 0){
                try {
                    file.write("Attribute : " + value + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(FieldDeclaration declaration, Void arg)
    {
        if(!declaration.isPrivate()){
            return ;
        }
        String attributeName = declaration.getVariable(0).toString().split(" ")[0];
        String attributeNameFirstLetterUpper = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        fieldsList.add(attributeNameFirstLetterUpper);
    }
}
package fr.istic.vv;

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

    public List<String> fieldsList = new ArrayList<>();
    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for(TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {
        if(!declaration.isPublic()) return;
       //System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
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
        for (String field : fieldsList) {
            System.out.println(field);
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
        if(!declaration.isPrivate() ) return;
        String methodName = declaration.getNameAsString();
        if(methodName.substring(0, 2).equals("get")){
            if(fieldsList.contains(methodName.substring(3))){
                fieldsList.remove(methodName.substring(3));
            }
        }
        //System.out.println(" " + declaration.getDeclarationAsString(true, true));
    }

    @Override
    public void visit(FieldDeclaration declaration, Void arg)
    {
        if(!declaration.isPrivate()){
            return ;
        }
        String field = declaration.getVariable(0).toString().substring(0, 1).toUpperCase() + declaration.getVariable(0).toString().substring(1);
        // System.out.println(" " + field);
        fieldsList.add(field);
    }
}
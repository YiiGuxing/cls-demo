// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SimpleHTMLVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull SimpleHTMLAttribute o) {
    visitPsiElement(o);
  }

  public void visitAttributeValue(@NotNull SimpleHTMLAttributeValue o) {
    visitPsiElement(o);
  }

  public void visitComment(@NotNull SimpleHTMLComment o) {
    visitPsiElement(o);
  }

  public void visitTag(@NotNull SimpleHTMLTag o) {
    visitPsiElement(o);
  }

  public void visitText(@NotNull SimpleHTMLText o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}

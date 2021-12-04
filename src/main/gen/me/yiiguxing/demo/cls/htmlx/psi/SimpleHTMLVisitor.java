// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;

public class SimpleHTMLVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull SimpleHTMLAttribute o) {
    visitElement(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitAttributeValue(@NotNull SimpleHTMLAttributeValue o) {
    visitElement(o);
  }

  public void visitComment(@NotNull SimpleHTMLComment o) {
    visitElement(o);
    // visitPsiComment(o);
  }

  public void visitDoctype(@NotNull SimpleHTMLDoctype o) {
    visitElement(o);
  }

  public void visitDocument(@NotNull SimpleHTMLDocument o) {
    visitElement(o);
  }

  public void visitEmptyTag(@NotNull SimpleHTMLEmptyTag o) {
    visitTag(o);
  }

  public void visitNotEmptyTag(@NotNull SimpleHTMLNotEmptyTag o) {
    visitTag(o);
  }

  public void visitProlog(@NotNull SimpleHTMLProlog o) {
    visitElement(o);
  }

  public void visitTag(@NotNull SimpleHTMLTag o) {
    visitPsiNamedElement(o);
  }

  public void visitText(@NotNull SimpleHTMLText o) {
    visitElement(o);
  }

  public void visitPsiNamedElement(@NotNull PsiNamedElement o) {
    visitElement(o);
  }

  public void visitElement(@NotNull SimpleHTMLElement o) {
    super.visitElement(o);
  }

}

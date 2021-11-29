// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiComment;

public class SimpleHTMLVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull SimpleHTMLAttribute o) {
    visitElement(o);
  }

  public void visitAttributeValue(@NotNull SimpleHTMLAttributeValue o) {
    visitElement(o);
  }

  public void visitComment(@NotNull SimpleHTMLComment o) {
    visitElement(o);
    // visitPsiComment(o);
  }

  public void visitEmptyTag(@NotNull SimpleHTMLEmptyTag o) {
    visitTag(o);
  }

  public void visitNotEmptyTag(@NotNull SimpleHTMLNotEmptyTag o) {
    visitTag(o);
  }

  public void visitTag(@NotNull SimpleHTMLTag o) {
    visitElement(o);
  }

  public void visitText(@NotNull SimpleHTMLText o) {
    visitElement(o);
  }

  public void visitElement(@NotNull SimpleHTMLElement o) {
    super.visitElement(o);
  }

}

// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLComment;
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLVisitor;
import org.jetbrains.annotations.NotNull;

public class SimpleHTMLCommentImpl extends SimpleHTMLCommentBase implements SimpleHTMLComment {

  public SimpleHTMLCommentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitComment(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

}

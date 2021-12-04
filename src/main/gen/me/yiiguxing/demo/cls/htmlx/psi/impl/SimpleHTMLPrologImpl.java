// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*;
import me.yiiguxing.demo.cls.htmlx.psi.*;

public class SimpleHTMLPrologImpl extends SimpleHTMLElementBase implements SimpleHTMLProlog {

  public SimpleHTMLPrologImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitProlog(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SimpleHTMLComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SimpleHTMLComment.class);
  }

  @Override
  @Nullable
  public SimpleHTMLDoctype getDoctype() {
    return findChildByClass(SimpleHTMLDoctype.class);
  }

}

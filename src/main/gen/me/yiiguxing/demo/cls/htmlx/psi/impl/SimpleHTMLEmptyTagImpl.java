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

public class SimpleHTMLEmptyTagImpl extends SimpleHTMLTagImpl implements SimpleHTMLEmptyTag {

  public SimpleHTMLEmptyTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitEmptyTag(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SimpleHTMLAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SimpleHTMLAttribute.class);
  }

}

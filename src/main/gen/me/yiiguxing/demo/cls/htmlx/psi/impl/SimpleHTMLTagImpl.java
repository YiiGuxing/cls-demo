// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLAttribute;
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTag;
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class SimpleHTMLTagImpl extends SimpleHTMLElementBase implements SimpleHTMLTag {

  public SimpleHTMLTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitTag(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getTagNameElement() {
    return SimpleHTMLPsiImplUtil.getTagNameElement(this);
  }

  @Override
  @NotNull
  public List<SimpleHTMLAttribute> getAttributeList() {
    return SimpleHTMLPsiImplUtil.getAttributeList(this);
  }

}

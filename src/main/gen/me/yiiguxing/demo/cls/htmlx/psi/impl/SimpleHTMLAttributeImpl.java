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

public class SimpleHTMLAttributeImpl extends SimpleHTMLElementBase implements SimpleHTMLAttribute {

  public SimpleHTMLAttributeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SimpleHTMLAttributeValue getAttributeValue() {
    return findChildByClass(SimpleHTMLAttributeValue.class);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return SimpleHTMLPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public String getName() {
    return SimpleHTMLPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement getAttributeNameElement() {
    return SimpleHTMLPsiImplUtil.getAttributeNameElement(this);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return SimpleHTMLPsiImplUtil.getNameIdentifier(this);
  }

}

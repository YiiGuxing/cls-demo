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

public class SimpleHTMLAttributeValueImpl extends SimpleHTMLElementBase implements SimpleHTMLAttributeValue {

  public SimpleHTMLAttributeValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleHTMLVisitor visitor) {
    visitor.visitAttributeValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleHTMLVisitor) accept((SimpleHTMLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public String getValue() {
    return SimpleHTMLPsiImplUtil.getValue(this);
  }

}

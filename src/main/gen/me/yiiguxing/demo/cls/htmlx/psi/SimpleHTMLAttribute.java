// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface SimpleHTMLAttribute extends SimpleHTMLElement, PsiNameIdentifierOwner {

  @Nullable
  SimpleHTMLAttributeValue getAttributeValue();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  String getName();

  @NotNull
  PsiElement getAttributeNameElement();

  @NotNull
  PsiElement getNameIdentifier();

}

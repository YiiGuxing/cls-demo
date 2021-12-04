// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;

public interface SimpleHTMLTag extends PsiNamedElement, SimpleHTMLElement {

  @Nullable
  PsiElement getTagNameElement();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  List<SimpleHTMLAttribute> getAttributeList();

}

// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleHTMLTag extends SimpleHTMLElement {

  @NotNull
  List<SimpleHTMLAttribute> getAttributeList();

  @NotNull
  List<SimpleHTMLComment> getCommentList();

  @NotNull
  List<SimpleHTMLTag> getTagList();

  @NotNull
  List<SimpleHTMLText> getTextList();

}

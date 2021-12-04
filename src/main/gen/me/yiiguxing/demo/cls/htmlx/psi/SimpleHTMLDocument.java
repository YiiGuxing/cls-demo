// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleHTMLDocument extends SimpleHTMLElement {

  @NotNull
  List<SimpleHTMLComment> getCommentList();

  @NotNull
  SimpleHTMLProlog getProlog();

  @NotNull
  List<SimpleHTMLTag> getTagList();

  @Nullable
  SimpleHTMLTag getRootTag();

}

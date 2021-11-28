// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.yiiguxing.demo.cls.htmlx.psi.impl.*;

public interface SimpleHTMLTypes {

  IElementType ATTRIBUTE = new SimpleHTMLElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_VALUE = new SimpleHTMLElementType("ATTRIBUTE_VALUE");
  IElementType COMMENT = new SimpleHTMLElementType("COMMENT");
  IElementType TAG = new SimpleHTMLElementType("TAG");
  IElementType TEXT = new SimpleHTMLElementType("TEXT");

  IElementType ATTRIBUTE_NAME = new SimpleHTMLTokenType("ATTRIBUTE_NAME");
  IElementType ATTRIBUTE_VALUE_DEFINER_DQ = new SimpleHTMLTokenType("\"");
  IElementType ATTRIBUTE_VALUE_DEFINER_SQ = new SimpleHTMLTokenType("'");
  IElementType ATTR_VALUE = new SimpleHTMLTokenType("ATTR_VALUE");
  IElementType COMMENT_END = new SimpleHTMLTokenType("-->");
  IElementType COMMENT_START = new SimpleHTMLTokenType("<!--");
  IElementType COMMENT_TEXT = new SimpleHTMLTokenType("COMMENT_TEXT");
  IElementType DOCTYPE = new SimpleHTMLTokenType("<!DOCTYPE html>");
  IElementType EMPTY_TAG_END = new SimpleHTMLTokenType("/>");
  IElementType END_TAG_START = new SimpleHTMLTokenType("</");
  IElementType EQ = new SimpleHTMLTokenType("=");
  IElementType ROW_TEXT = new SimpleHTMLTokenType("ROW_TEXT");
  IElementType TAG_END = new SimpleHTMLTokenType(">");
  IElementType TAG_NAME = new SimpleHTMLTokenType("TAG_NAME");
  IElementType TAG_START = new SimpleHTMLTokenType("<");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new SimpleHTMLAttributeImpl(node);
      }
      else if (type == ATTRIBUTE_VALUE) {
        return new SimpleHTMLAttributeValueImpl(node);
      }
      else if (type == COMMENT) {
        return new SimpleHTMLCommentImpl(node);
      }
      else if (type == TAG) {
        return new SimpleHTMLTagImpl(node);
      }
      else if (type == TEXT) {
        return new SimpleHTMLTextImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

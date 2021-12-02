// This is a generated file. Not intended for manual editing.
package me.yiiguxing.demo.cls.htmlx.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimpleHTMLParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return SimpleHTMLFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(EMPTY_TAG, NOT_EMPTY_TAG, TAG),
  };

  /* ********************************************************** */
  // ATTRIBUTE_NAME ('=' AttributeValue)?
  public static boolean Attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attribute")) return false;
    if (!nextTokenIs(b, ATTRIBUTE_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, null);
    r = consumeToken(b, ATTRIBUTE_NAME);
    p = r; // pin = 1
    r = r && Attribute_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('=' AttributeValue)?
  private static boolean Attribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attribute_1")) return false;
    Attribute_1_0(b, l + 1);
    return true;
  }

  // '=' AttributeValue
  private static boolean Attribute_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Attribute_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ATTRIBUTE_ASSIGN);
    p = r; // pin = 1
    r = r && AttributeValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ATTRIBUTE_VALUE_TEXT | '"' ATTRIBUTE_VALUE_TEXT? '"' | "'" ATTRIBUTE_VALUE_TEXT? "'"
  public static boolean AttributeValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE_VALUE, "<attribute value>");
    r = consumeToken(b, ATTRIBUTE_VALUE_TEXT);
    if (!r) r = AttributeValue_1(b, l + 1);
    if (!r) r = AttributeValue_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '"' ATTRIBUTE_VALUE_TEXT? '"'
  private static boolean AttributeValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeValue_1")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ATTRIBUTE_VALUE_DEFINER_DQ);
    p = r; // pin = 1
    r = r && report_error_(b, AttributeValue_1_1(b, l + 1));
    r = p && consumeToken(b, ATTRIBUTE_VALUE_DEFINER_DQ) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ATTRIBUTE_VALUE_TEXT?
  private static boolean AttributeValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeValue_1_1")) return false;
    consumeToken(b, ATTRIBUTE_VALUE_TEXT);
    return true;
  }

  // "'" ATTRIBUTE_VALUE_TEXT? "'"
  private static boolean AttributeValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeValue_2")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ATTRIBUTE_VALUE_DEFINER_SQ);
    p = r; // pin = 1
    r = r && report_error_(b, AttributeValue_2_1(b, l + 1));
    r = p && consumeToken(b, ATTRIBUTE_VALUE_DEFINER_SQ) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ATTRIBUTE_VALUE_TEXT?
  private static boolean AttributeValue_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AttributeValue_2_1")) return false;
    consumeToken(b, ATTRIBUTE_VALUE_TEXT);
    return true;
  }

  /* ********************************************************** */
  // '<!--' COMMENT_TEXT* '-->'
  public static boolean Comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comment")) return false;
    if (!nextTokenIs(b, COMMENT_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMMENT, null);
    r = consumeToken(b, COMMENT_START);
    p = r; // pin = 1
    r = r && report_error_(b, Comment_1(b, l + 1));
    r = p && consumeToken(b, COMMENT_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // COMMENT_TEXT*
  private static boolean Comment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comment_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT_TEXT)) break;
      if (!empty_element_parsed_guard_(b, "Comment_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DOCTYPE_START DOCTYPE_NAME DOCTYPE_END
  public static boolean Doctype(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Doctype")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOCTYPE, "<doctype>");
    r = consumeTokens(b, 1, DOCTYPE_START, DOCTYPE_NAME, DOCTYPE_END);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, SimpleHTMLParser::Recover);
    return r || p;
  }

  /* ********************************************************** */
  // TagHead '/>'
  public static boolean EmptyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmptyTag")) return false;
    if (!nextTokenIs(b, TAG_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EMPTY_TAG, null);
    r = TagHead(b, l + 1);
    p = r; // pin = 1
    r = r && consumeToken(b, EMPTY_TAG_END);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TagStart TagContent* TagEnd
  public static boolean NotEmptyTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NotEmptyTag")) return false;
    if (!nextTokenIs(b, TAG_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NOT_EMPTY_TAG, null);
    r = TagStart(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, NotEmptyTag_1(b, l + 1));
    r = p && TagEnd(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // TagContent*
  private static boolean NotEmptyTag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NotEmptyTag_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TagContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NotEmptyTag_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !('<')
  static boolean Recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, TAG_START);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Doctype? (Tag | Comment)*
  static boolean SimpleHTMLFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleHTMLFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SimpleHTMLFile_0(b, l + 1);
    r = r && SimpleHTMLFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Doctype?
  private static boolean SimpleHTMLFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleHTMLFile_0")) return false;
    Doctype(b, l + 1);
    return true;
  }

  // (Tag | Comment)*
  private static boolean SimpleHTMLFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleHTMLFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SimpleHTMLFile_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SimpleHTMLFile_1", c)) break;
    }
    return true;
  }

  // Tag | Comment
  private static boolean SimpleHTMLFile_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SimpleHTMLFile_1_0")) return false;
    boolean r;
    r = Tag(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // NotEmptyTag | EmptyTag
  public static boolean Tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Tag")) return false;
    if (!nextTokenIs(b, TAG_START)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, TAG, null);
    r = NotEmptyTag(b, l + 1);
    if (!r) r = EmptyTag(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Tag | Comment | Text | STYLE_CODE | SCRIPT_CODE
  static boolean TagContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TagContent")) return false;
    boolean r;
    r = Tag(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    if (!r) r = Text(b, l + 1);
    if (!r) r = consumeToken(b, STYLE_CODE);
    if (!r) r = consumeToken(b, SCRIPT_CODE);
    return r;
  }

  /* ********************************************************** */
  // '</' END_TAG_NAME '>'
  static boolean TagEnd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TagEnd")) return false;
    if (!nextTokenIs(b, END_TAG_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 1, END_TAG_START, END_TAG_NAME, TAG_END);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '<' START_TAG_NAME Attribute*
  static boolean TagHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TagHead")) return false;
    if (!nextTokenIs(b, TAG_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 1, TAG_START, START_TAG_NAME);
    p = r; // pin = 1
    r = r && TagHead_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Attribute*
  private static boolean TagHead_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TagHead_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TagHead_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TagHead '>'
  static boolean TagStart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TagStart")) return false;
    if (!nextTokenIs(b, TAG_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TagHead(b, l + 1);
    r = r && consumeToken(b, TAG_END);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ROW_TEXT+
  public static boolean Text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Text")) return false;
    if (!nextTokenIs(b, ROW_TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ROW_TEXT);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ROW_TEXT)) break;
      if (!empty_element_parsed_guard_(b, "Text", c)) break;
    }
    exit_section_(b, m, TEXT, r);
    return r;
  }

}

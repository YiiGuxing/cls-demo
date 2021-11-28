package me.yiiguxing.demo.cls.htmlx.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*;

%%

%{
  public _SimpleHTMLLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SimpleHTMLLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

LETTER = [:letter:] | "_"
DIGIT =  [:digit:]

IDENT = {LETTER} ({LETTER} | {DIGIT})*

ATTRIBUTE_NAME = ({LETTER} | {DIGIT})+
ATTR_VALUE_SQ = [^']+
ATTR_VALUE_DQ = [^\"]+

ROW_TEXT = [^\s<]+

COMMENT_TEXT = [^"-->"]+

%state WAITING_TAG_NAME, WAITING_ATTRIBUTE_NAME, WAITING_ATTR_VALUE_SQ, WAITING_ATTR_VALUE_DQ, WAITING_TEXT, WAITING_COMMENT_TEXT

%%
<YYINITIAL> {
  {WHITE_SPACE}          { return WHITE_SPACE; }

  "<"                    { yybegin(WAITING_TAG_NAME); return TAG_START; }
  "</"                   { yybegin(WAITING_TAG_NAME); return END_TAG_START; }
  "/>"                   { return EMPTY_TAG_END; }
  "<!DOCTYPE html>"      { return DOCTYPE; }
  "<!--"                 { yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  "-->"                  { yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  {ROW_TEXT}             { return ROW_TEXT; }
}

<WAITING_TAG_NAME> {
  {IDENT}                { yybegin(WAITING_ATTRIBUTE_NAME); return TAG_NAME; }
}

<WAITING_ATTRIBUTE_NAME> {
  "="                    { return EQ; }
  "'"                    { yybegin(WAITING_ATTR_VALUE_SQ); return ATTRIBUTE_VALUE_DEFINER_SQ; }
  "\""                   { yybegin(WAITING_ATTR_VALUE_DQ); return ATTRIBUTE_VALUE_DEFINER_DQ; }
  {ATTRIBUTE_NAME}       { return ATTRIBUTE_NAME; }
}

<WAITING_ATTR_VALUE_SQ> {
  "'"                   { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE_DEFINER_SQ; }
  {ATTR_VALUE_SQ}       { return ATTR_VALUE; }
}

<WAITING_ATTR_VALUE_DQ> {
  "\""                   { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE_DEFINER_DQ; }
  {ATTR_VALUE_DQ}        { return ATTR_VALUE; }
}

<YYINITIAL,WAITING_TAG_NAME,WAITING_ATTRIBUTE_NAME> {
  {WHITE_SPACE}          { return WHITE_SPACE; }
  ">"                    { yybegin(YYINITIAL); return TAG_END; }
  "/>"                   { yybegin(YYINITIAL); return EMPTY_TAG_END; }
}

<WAITING_COMMENT_TEXT> {
  "-->"                  { yybegin(YYINITIAL); return COMMENT_END; }
  {COMMENT_TEXT}         { return COMMENT_TEXT; }
}

[^] { return BAD_CHARACTER; }

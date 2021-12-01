package me.yiiguxing.demo.cls.htmlx.parser;

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

DOCTYPE_NAME = [hH][tT][mM][lL]

ALPHA = [:letter:]
DIGIT = [:digit:]

NAME_START = {ALPHA} | "_"
NAME_BODY = {NAME_START} | {DIGIT} | "-"
NAME = {NAME_START} ({NAME_BODY}) *
ATTRIBUTE_NAME = ({NAME_BODY})+

PRE_START_TAG = "<" {NAME_START}

ATTR_VALUE = [^'\"\s>]+
ATTR_VALUE_SQ = [^']+
ATTR_VALUE_DQ = [^\"]+

ROW_TEXT = [^\s<]+
COMMENT_TEXT = [^->]+

%state WAITING_DOCTYPE
%state WAITING_START_TAG_NAME
%state WAITING_END_TAG_NAME
%state WAITING_ATTRIBUTE_NAME
%state WAITING_ATTR_VALUE
%state WAITING_ATTR_VALUE_SQ
%state WAITING_ATTR_VALUE_DQ
%state WAITING_TEXT
%state WAITING_COMMENT_TEXT

%%
<YYINITIAL> {
  "<!DOCTYPE"            { yybegin(WAITING_DOCTYPE); return DOCTYPE_START; }
  "</"                   { yybegin(WAITING_END_TAG_NAME); return END_TAG_START; }
  {ROW_TEXT}             { return ROW_TEXT; }
}

<YYINITIAL,WAITING_DOCTYPE> {
  {WHITE_SPACE}          { return WHITE_SPACE; }

  {PRE_START_TAG}        { yybegin(WAITING_START_TAG_NAME); yypushback(1); return TAG_START; }
  "<!--"                 { yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  "<"                    { yybegin(YYINITIAL); return ROW_TEXT; }
}

<WAITING_DOCTYPE> {
  {DOCTYPE_NAME}         { return DOCTYPE_NAME; }
  ">"                    { yybegin(YYINITIAL); return DOCTYPE_END; }
}

<WAITING_START_TAG_NAME> {
  {NAME}                 { yybegin(WAITING_ATTRIBUTE_NAME); return START_TAG_NAME; }
}

<WAITING_END_TAG_NAME> {
  {NAME}                 { return END_TAG_NAME; }
}

<WAITING_ATTRIBUTE_NAME> {
  {ATTRIBUTE_NAME}       { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_NAME; }
  "="                    { yybegin(WAITING_ATTR_VALUE); return ATTRIBUTE_ASSIGN; }
}

<WAITING_ATTR_VALUE> {
  "'"                    { yybegin(WAITING_ATTR_VALUE_SQ); return ATTRIBUTE_VALUE_DEFINER_SQ; }
  "\""                   { yybegin(WAITING_ATTR_VALUE_DQ); return ATTRIBUTE_VALUE_DEFINER_DQ; }
  {ATTR_VALUE}           { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE_TEXT; }
}

<WAITING_ATTR_VALUE_SQ> {
  "'"                    { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE_DEFINER_SQ; }
  {ATTR_VALUE_SQ}        { return ATTRIBUTE_VALUE_TEXT; }
}

<WAITING_ATTR_VALUE_DQ> {
  "\""                   { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE_DEFINER_DQ; }
  {ATTR_VALUE_DQ}        { return ATTRIBUTE_VALUE_TEXT; }
}

<WAITING_START_TAG_NAME,
WAITING_END_TAG_NAME,
WAITING_ATTRIBUTE_NAME,
WAITING_ATTR_VALUE> {
  {WHITE_SPACE}          {
                            if (yystate()!=WAITING_END_TAG_NAME)
                                return WHITE_SPACE; yybegin(YYINITIAL);
                            yybegin(YYINITIAL);
                            yypushback(yytext().length());
                         }
  {PRE_START_TAG}        { yybegin(WAITING_START_TAG_NAME); yypushback(1); return TAG_START; }
  "</"                   { yybegin(WAITING_END_TAG_NAME); return END_TAG_START; }
  ">"                    { yybegin(YYINITIAL); return TAG_END; }
  "/>"                   { yybegin(YYINITIAL); return EMPTY_TAG_END; }
  "<!--"                 { yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  .                      { yybegin(YYINITIAL); yypushback(yytext().length()); }
}

<WAITING_COMMENT_TEXT> {
  "-->"                  { yybegin(YYINITIAL); return COMMENT_END; }
  "-" | ">"              { return COMMENT_TEXT; }
  {COMMENT_TEXT}         { return COMMENT_TEXT; }
}

[^]                      { return BAD_CHARACTER; }

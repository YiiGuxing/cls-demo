package me.yiiguxing.demo.cls.htmlx.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*;

%%

%{
  int nextState;

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
STYLE_NAME = [sS][tT][yY][lL][eE]
SCRIPT_NAME = [sS][cC][rR][iI][pP][tT]

ALPHA = [:letter:]
DIGIT = [:digit:]

NAME_START = {ALPHA} | "_"
NAME_BODY = {NAME_START} | {DIGIT} | "-"
NAME = {NAME_START} ({NAME_BODY}) *
ATTRIBUTE_NAME = ({NAME_BODY})+

PRE_START_TAG = "<" {NAME_START}

ATTR_VALUE = [^'\"\s>] [^\s>]*
ATTR_VALUE_SQ = [^']+
ATTR_VALUE_DQ = [^\"]+

STYLE_CODE = ~("</" {STYLE_NAME} ">")
SCRIPT_CODE = ~("</" {SCRIPT_NAME} ">")

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
%state START_STYLE_CODE
%state START_SCRIPT_CODE

%%
<YYINITIAL> {
  "<!DOCTYPE"            { yybegin(WAITING_DOCTYPE); return DOCTYPE_START; }
  "</"                   { yybegin(WAITING_END_TAG_NAME); return END_TAG_START; }
  {ROW_TEXT}             { return ROW_TEXT; }
}

<YYINITIAL,WAITING_DOCTYPE> {
  {WHITE_SPACE}          { return WHITE_SPACE; }

  {PRE_START_TAG}        { nextState=YYINITIAL; yybegin(WAITING_START_TAG_NAME); yypushback(1); return TAG_START; }
  "<!--"                 { yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  "<"                    { yybegin(YYINITIAL); return ROW_TEXT; }
}

<WAITING_DOCTYPE> {
  {DOCTYPE_NAME}         { return DOCTYPE_NAME; }
  ">"                    { yybegin(YYINITIAL); return DOCTYPE_END; }
}

<WAITING_START_TAG_NAME> {
  {STYLE_NAME}           { nextState=START_STYLE_CODE; yybegin(WAITING_ATTRIBUTE_NAME); return START_TAG_NAME; }
  {SCRIPT_NAME}          { nextState=START_SCRIPT_CODE; yybegin(WAITING_ATTRIBUTE_NAME); return START_TAG_NAME; }
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
                                return WHITE_SPACE;
                            yybegin(YYINITIAL);
                            yypushback(yytext().length());
                         }
  {PRE_START_TAG}        { nextState=YYINITIAL; yybegin(WAITING_START_TAG_NAME); yypushback(1); return TAG_START; }
  "</"                   { yybegin(WAITING_END_TAG_NAME); return END_TAG_START; }
  ">"                    {
                            if (yystate()==WAITING_ATTRIBUTE_NAME||yystate()==WAITING_ATTR_VALUE) {
                                yybegin(nextState); nextState=YYINITIAL;
                            } else {
                                yybegin(YYINITIAL);
                            }
                            return TAG_END;
                         }
  "/>"                   { nextState=YYINITIAL; yybegin(YYINITIAL); return EMPTY_TAG_END; }
  "<!--"                 { nextState=YYINITIAL; yybegin(WAITING_COMMENT_TEXT); return COMMENT_START; }
  .                      { yybegin(YYINITIAL); yypushback(yytext().length()); }
}

<START_STYLE_CODE> {
  {STYLE_CODE}           { nextState=YYINITIAL; yybegin(YYINITIAL); yypushback(8); return STYLE_CODE; }
  [^]                    { return STYLE_CODE; }
}

<START_SCRIPT_CODE> {
  {SCRIPT_CODE}          { nextState=YYINITIAL; yybegin(YYINITIAL); yypushback(9); return SCRIPT_CODE; }
  [^]                    { return SCRIPT_CODE; }
}

<WAITING_COMMENT_TEXT> {
  "-->"                  { yybegin(YYINITIAL); return COMMENT_END; }
  "-" | ">"              { return COMMENT_TEXT; }
  {COMMENT_TEXT}         { return COMMENT_TEXT; }
}

[^]                      { return BAD_CHARACTER; }

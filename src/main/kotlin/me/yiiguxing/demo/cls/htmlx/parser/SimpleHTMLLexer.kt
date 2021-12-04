package me.yiiguxing.demo.cls.htmlx.parser

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

private val MERGING_TOKENS = TokenSet.create(
    TokenType.WHITE_SPACE,
    TokenType.BAD_CHARACTER,
    SimpleHTMLTypes.STYLE_CODE,
    SimpleHTMLTypes.SCRIPT_CODE,
    SimpleHTMLTypes.COMMENT_TEXT,
    SimpleHTMLTypes.ROW_TEXT,
)

class SimpleHTMLLexer : MergingLexerAdapter(
    FlexAdapter(_SimpleHTMLLexer()),
    MERGING_TOKENS
)
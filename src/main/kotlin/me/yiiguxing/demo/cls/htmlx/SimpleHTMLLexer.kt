package me.yiiguxing.demo.cls.htmlx

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet
import me.yiiguxing.demo.cls.htmlx.parser._SimpleHTMLLexer
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

private val COMMENT_TEXTS = TokenSet.create(SimpleHTMLTypes.COMMENT_TEXT)

class SimpleHTMLLexer : MergingLexerAdapter(
    FlexAdapter(_SimpleHTMLLexer()),
    COMMENT_TEXTS
)
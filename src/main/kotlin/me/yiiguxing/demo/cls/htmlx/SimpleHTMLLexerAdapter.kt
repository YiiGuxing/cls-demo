package me.yiiguxing.demo.cls.htmlx

import com.intellij.lexer.FlexAdapter
import me.yiiguxing.demo.cls.htmlx.parser._SimpleHTMLLexer

class SimpleHTMLLexerAdapter : FlexAdapter(_SimpleHTMLLexer())
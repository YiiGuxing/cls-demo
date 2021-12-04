package me.yiiguxing.demo.cls.htmlx.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.util.containers.MultiMap
import me.yiiguxing.demo.cls.htmlx.parser.SimpleHTMLLexer
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*


class SimpleHTMLSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer = SimpleHTMLLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(SimpleHTMLHighlighterColors.DEFAULT_CODE, keyMap[tokenType].toTypedArray())
    }

    companion object {
        private val keyMap: MultiMap<IElementType, TextAttributesKey> = MultiMap.create()

        init {
            for (type in arrayOf(DOCTYPE_START, DOCTYPE_END)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.DOCTYPE)
            }

            for (type in arrayOf(TAG_START, TAG_END, END_TAG_START, EMPTY_TAG_END)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.TAG)
            }

            for (type in arrayOf(START_TAG_NAME, END_TAG_NAME)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.TAG_NAME)
            }

            for (type in arrayOf(DOCTYPE_NAME, ATTRIBUTE_NAME)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.ATTRIBUTE_NAME)
            }

            keyMap.putValue(ATTRIBUTE_ASSIGN, SimpleHTMLHighlighterColors.ATTRIBUTE_ASSIGN)

            for (type in arrayOf(ATTRIBUTE_VALUE_TEXT, ATTRIBUTE_VALUE_DEFINER_SQ, ATTRIBUTE_VALUE_DEFINER_DQ)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.ATTRIBUTE_VALUE)
            }

            for (type in arrayOf(COMMENT_START, COMMENT_TEXT, COMMENT_END)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.COMMENT)
            }

            keyMap.putValue(STYLE_CODE, SimpleHTMLHighlighterColors.STYLE_CODE)
            keyMap.putValue(SCRIPT_CODE, SimpleHTMLHighlighterColors.SCRIPT_CODE)
            keyMap.putValue(TokenType.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER)
        }

    }
}

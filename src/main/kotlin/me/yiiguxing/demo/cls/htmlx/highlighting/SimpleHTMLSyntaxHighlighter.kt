package me.yiiguxing.demo.cls.htmlx.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.util.containers.MultiMap
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLLexerAdapter
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*


class SimpleHTMLSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer = SimpleHTMLLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return pack(SimpleHTMLHighlighterColors.DEFAULT_CODE, keyMap[tokenType].toTypedArray())
    }

    companion object {
        private val keyMap: MultiMap<IElementType, TextAttributesKey> = MultiMap.create()

        init {
            keyMap.putValue(DOCTYPE, SimpleHTMLHighlighterColors.DOCTYPE)

            for (type in arrayOf(TAG_START, TAG_END, END_TAG_START, EMPTY_TAG_END)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.TAG)
            }

            keyMap.putValue(TAG_NAME, SimpleHTMLHighlighterColors.TAG_NAME)
            keyMap.putValue(ATTRIBUTE_NAME, SimpleHTMLHighlighterColors.ATTRIBUTE_NAME)
            keyMap.putValue(EQ, SimpleHTMLHighlighterColors.ATTRIBUTE_ASSIGN)

            for (type in arrayOf(ATTR_VALUE, ATTRIBUTE_VALUE_DEFINER_SQ, ATTRIBUTE_VALUE_DEFINER_DQ)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.ATTRIBUTE_VALUE)
            }

            for (type in arrayOf(COMMENT_START, COMMENT_TEXT, COMMENT_END)) {
                keyMap.putValue(type, SimpleHTMLHighlighterColors.COMMENT)
            }
        }

    }
}

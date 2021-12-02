package me.yiiguxing.demo.cls.htmlx.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

object SimpleHTMLHighlighterColors {

    @JvmField
    val DEFAULT_CODE = createTextAttributesKey("HTMLX_CODE", HighlighterColors.TEXT)

    @JvmField
    val DOCTYPE = createTextAttributesKey("HTMLX_DOCTYPE", DefaultLanguageHighlighterColors.KEYWORD)

    @JvmField
    val TAG = createTextAttributesKey("HTMLX_TAG", DefaultLanguageHighlighterColors.IDENTIFIER)

    @JvmField
    val TAG_NAME = createTextAttributesKey("HTMLX_TAG_NAME", TAG)

    @JvmField
    val ATTRIBUTE_NAME = createTextAttributesKey("HTMLX_ATTRIBUTE_NAME", DefaultLanguageHighlighterColors.IDENTIFIER)

    @JvmField
    val ATTRIBUTE_ASSIGN = createTextAttributesKey("HTMLX_ATTRIBUTE_ASSIGN", ATTRIBUTE_NAME)

    @JvmField
    val ATTRIBUTE_VALUE = createTextAttributesKey("HTMLX_ATTRIBUTE_VALUE", DefaultLanguageHighlighterColors.STRING)

    @JvmField
    val STYLE_CODE = createTextAttributesKey("HTMLX_STYLE_CODE", EditorColors.INJECTED_LANGUAGE_FRAGMENT)

    @JvmField
    val SCRIPT_CODE = createTextAttributesKey("HTMLX_SCRIPT_CODE", EditorColors.INJECTED_LANGUAGE_FRAGMENT)

    @JvmField
    val COMMENT = createTextAttributesKey("HTMLX_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)

}
package me.yiiguxing.demo.cls.htmlx.ui

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import me.yiiguxing.demo.cls.htmlx.Icons
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.ATTRIBUTE_ASSIGN
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.ATTRIBUTE_NAME
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.ATTRIBUTE_VALUE
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.COMMENT
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.DEFAULT_CODE
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.DOCTYPE
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.TAG
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLHighlighterColors.TAG_NAME
import me.yiiguxing.demo.cls.htmlx.highlighting.SimpleHTMLSyntaxHighlighter
import javax.swing.Icon

class SimpleHTMLColorSettingsPage : ColorSettingsPage {

    override fun getIcon(): Icon = Icons.htmlx

    override fun getDisplayName(): String = "Simple HTML"

    override fun getHighlighter(): SyntaxHighlighter = SimpleHTMLSyntaxHighlighter()

    override fun getAttributeDescriptors(): Array<out AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDemoText(): String {
        return """
            <!DOCTYPE html>
            <!--
            *        Sample comment
            -->
            <HTML>
            <head>
            <title>Simple HTML Support</title>
            </head>
            <body>
            <h1>Simple HTML Support</h1>
            <p><br/><b><IMG width="18" height='12' src="images/hg.gif" />
            A simple implementation of HTML support plug-in.</b><br/><br/></p>
            
            <button disabled>Click Me!</button>
            </body>
            </html>
        """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }

    companion object {
        private val DESCRIPTORS: Array<out AttributesDescriptor> = arrayOf(
            AttributesDescriptor("Default code", DEFAULT_CODE),
            AttributesDescriptor("Doctype", DOCTYPE),
            AttributesDescriptor("Tag", TAG),
            AttributesDescriptor("Tag name", TAG_NAME),
            AttributesDescriptor("Attribute name", ATTRIBUTE_NAME),
            AttributesDescriptor("Attribute assign", ATTRIBUTE_ASSIGN),
            AttributesDescriptor("Attribute value", ATTRIBUTE_VALUE),
            AttributesDescriptor("Comment", COMMENT),
        )
    }
}
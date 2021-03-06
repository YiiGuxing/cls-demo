package me.yiiguxing.demo.cls.htmlx

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon


object SimpleHTMLFileType : LanguageFileType(SimpleHTMLLanguage) {

    override fun getName(): String = "Simple HTML File"

    override fun getDescription(): String = "Simple HTML File"

    override fun getDefaultExtension(): String = SIMPLE_HTML_FILE_EXTENSION

    override fun getIcon(): Icon = Icons.htmlx
}
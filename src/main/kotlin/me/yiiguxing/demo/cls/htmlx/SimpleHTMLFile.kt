package me.yiiguxing.demo.cls.htmlx

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLDocument

class SimpleHTMLFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SimpleHTMLLanguage) {
    override fun getFileType(): FileType = SimpleHTMLFileType

    val document: SimpleHTMLDocument?
        get() = PsiTreeUtil.getChildOfType(this, SimpleHTMLDocument::class.java)

    override fun toString(): String {
        return "SimpleHTMLFile:$name"
    }

}
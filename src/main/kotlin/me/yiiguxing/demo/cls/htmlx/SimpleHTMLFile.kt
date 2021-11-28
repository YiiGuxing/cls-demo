package me.yiiguxing.demo.cls.htmlx

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class SimpleHTMLFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SimpleHTMLLanguage) {
    override fun getFileType(): FileType = SimpleHTMLFileType
}
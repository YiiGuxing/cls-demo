package me.yiiguxing.demo.cls.htmlx.structureView

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile

class SimpleHTMLFileTreeElement(file: SimpleHTMLFile) : PsiTreeElementBase<SimpleHTMLFile>(file) {
    override fun getPresentableText(): String = "Simple HTML File"

    override fun getChildrenBase(): Collection<StructureViewTreeElement> {
        return element?.document?.tagList?.map { SimpleHTMLTagTreeElement(it) } ?: emptyList()
    }
}
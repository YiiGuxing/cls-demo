package me.yiiguxing.demo.cls.htmlx.structureView

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile

class SimpleHTMLStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder? {
        if (psiFile !is SimpleHTMLFile) {
            return null
        }

        return object : TreeBasedStructureViewBuilder() {
            override fun isRootNodeShown(): Boolean = false

            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return SimpleHTMLStructureViewModel(psiFile, editor)
            }
        }
    }
}
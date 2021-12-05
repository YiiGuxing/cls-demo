package me.yiiguxing.demo.cls.htmlx.structureView

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.editor.Editor
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag

class SimpleHTMLStructureViewModel(file: SimpleHTMLFile, editor: Editor?) :
    StructureViewModelBase(file, editor, SimpleHTMLFileTreeElement(file)),

    StructureViewModel.ElementInfoProvider {
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return element.value is SimpleHTMLNotEmptyTag
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is SimpleHTMLEmptyTag
    }

}
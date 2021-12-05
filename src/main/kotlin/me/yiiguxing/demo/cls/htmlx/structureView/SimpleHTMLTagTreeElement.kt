package me.yiiguxing.demo.cls.htmlx.structureView

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTag

class SimpleHTMLTagTreeElement(tag: SimpleHTMLTag) : PsiTreeElementBase<SimpleHTMLTag>(tag) {

    override fun getPresentableText(): String? = element?.name

    override fun isSearchInLocationString(): Boolean = true

    override fun getLocationString(): String? {
        (element as? SimpleHTMLNotEmptyTag)?.let { tag ->
            val contentText = tag.textList.joinToString(" ") { it.text }
            return@getLocationString if (contentText.length > 30) {
                contentText.take(30) + "..."
            } else {
                contentText
            }
        }
        return super.getLocationString()
    }

    override fun getChildrenBase(): Collection<StructureViewTreeElement> {
        return (element as? SimpleHTMLNotEmptyTag)?.tagList?.map { SimpleHTMLTagTreeElement(it) } ?: emptyList()
    }
}
package me.yiiguxing.demo.cls.htmlx.inspection

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.isHtmlTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class TagNameInspection : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val type = element.elementType
        if (type != SimpleHTMLTypes.START_TAG_NAME && type != SimpleHTMLTypes.END_TAG_NAME) {
            return
        }

        val tagName = element.text
        if (!element.text.isHtmlTag) {
            holder.newAnnotation(HighlightSeverity.WARNING, "Unknown tag $tagName")
                .range(element)
                .create()
        }
    }

}
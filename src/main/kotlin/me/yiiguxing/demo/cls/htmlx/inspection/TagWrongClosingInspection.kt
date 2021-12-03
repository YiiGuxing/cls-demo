package me.yiiguxing.demo.cls.htmlx.inspection

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag

class TagWrongClosingInspection : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is SimpleHTMLNotEmptyTag) {
            val startTagName = element.startTagNameElement ?: return
            val endTagName = element.endTagNameElement ?: return
            if (startTagName.text != endTagName.text) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Tag has wrong closing tag")
                    .range(startTagName)
                    .create()
            }
        }
    }

}
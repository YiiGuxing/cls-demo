package me.yiiguxing.demo.cls.htmlx.inspection

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class TagWrongClosingInspection : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == SimpleHTMLTypes.START_TAG_NAME) {
            val parent = element.parent
            if (parent is SimpleHTMLNotEmptyTag) {
                val endTagNameElement = parent.endTagNameElement ?: return
                val startName = element.text
                val endName = endTagNameElement.text
                if (startName != endName) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Tag has wrong closing tag")
                        .range(element)
                        .withFix(FixTagNameIntentionAction(startName, endName))
                        .create()
                }
            }
        }
    }

}
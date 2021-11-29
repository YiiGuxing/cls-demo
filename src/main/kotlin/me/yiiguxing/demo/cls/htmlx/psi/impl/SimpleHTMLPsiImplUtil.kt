package me.yiiguxing.demo.cls.htmlx.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLAttribute
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

object SimpleHTMLPsiImplUtil {

    @JvmStatic
    fun getAttributeList(tag: SimpleHTMLTag): List<SimpleHTMLAttribute> {
        return PsiTreeUtil.getChildrenOfTypeAsList(tag, SimpleHTMLAttribute::class.java)
    }

    @JvmStatic
    fun getTagNameElement(tag: SimpleHTMLTag): PsiElement? {
        var child: PsiElement? = tag.firstChild ?: return null
        while (child != null) {
            if (child.node.elementType == SimpleHTMLTypes.START_TAG_NAME) {
                return child
            }
            child = child.nextSibling
        }

        return null
    }

    @JvmStatic
    fun getStartTagNameElement(tag: SimpleHTMLNotEmptyTag): PsiElement? {
        return getTagNameElement(tag)
    }

    @JvmStatic
    fun getEndTagNameElement(tag: SimpleHTMLNotEmptyTag): PsiElement? {
        var child: PsiElement? = tag.lastChild ?: return null
        while (child != null) {
            if (child.node.elementType == SimpleHTMLTypes.END_TAG_NAME) {
                return child
            }
            child = child.prevSibling
        }

        return null
    }

}
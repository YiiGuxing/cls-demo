package me.yiiguxing.demo.cls.htmlx.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.psi.*

object SimpleHTMLPsiImplUtil {

    @JvmStatic
    fun getRootTag(document: SimpleHTMLDocument): SimpleHTMLTag? {
        return document.tagList.firstOrNull()
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
    fun getName(tag: SimpleHTMLTag): String {
        return getTagNameElement(tag)?.text ?: ""
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


    @JvmStatic
    fun setName(tag: SimpleHTMLTag, name: String): PsiElement {
        val oldStartTagName = getTagNameElement(tag)?.node
        val oldEndTagName = (tag as? SimpleHTMLNotEmptyTag)?.let { getEndTagNameElement(it) }?.node

        if (oldStartTagName != null || oldEndTagName != null) {
            val dummyTag = SimpleHTMLElementFactory.getInstance(tag.project).createTagElement(name)
            oldStartTagName?.let { tag.node.replaceChild(it, dummyTag.startTagNameElement!!.node) }
            oldEndTagName?.let { tag.node.replaceChild(it, dummyTag.endTagNameElement!!.node) }
        }

        return tag
    }

    @JvmStatic
    fun getAttributeValue(tag: SimpleHTMLTag, name: String): String? {
        var child: PsiElement? = tag.firstChild
        while (child != null) {
            (child as? SimpleHTMLAttribute)?.let {
                if (it.name.equals(name, true)) {
                    return@getAttributeValue it.attributeValue?.value
                }
            }
            child = child.nextSibling
        }

        return null
    }

    @JvmStatic
    fun getAttributeList(tag: SimpleHTMLTag): List<SimpleHTMLAttribute> {
        return PsiTreeUtil.getChildrenOfTypeAsList(tag, SimpleHTMLAttribute::class.java)
    }

    @JvmStatic
    fun getAttributeNameElement(attribute: SimpleHTMLAttribute): PsiElement {
        val attributeNameElement = attribute.firstChild
        assert(attributeNameElement != null && attributeNameElement.elementType == SimpleHTMLTypes.ATTRIBUTE_NAME)
        return attributeNameElement
    }

    @JvmStatic
    fun getNameIdentifier(attribute: SimpleHTMLAttribute): PsiElement {
        return getAttributeNameElement(attribute)
    }

    @JvmStatic
    fun getName(attribute: SimpleHTMLAttribute): String {
        return getAttributeNameElement(attribute).text
    }

    @JvmStatic
    fun setName(attribute: SimpleHTMLAttribute, name: String): PsiElement {
        val old = getAttributeNameElement(attribute).node
        if (old != null) {
            val nameElement = SimpleHTMLElementFactory.getInstance(attribute.project).createAttributeNameElement(name)
            attribute.node.replaceChild(old, nameElement.node)
        }

        return attribute
    }

    @JvmStatic
    fun getValue(attributeValue: SimpleHTMLAttributeValue): String? {
        return attributeValue.node.findChildByType(SimpleHTMLTypes.ATTRIBUTE_VALUE_TEXT)?.text
    }


}
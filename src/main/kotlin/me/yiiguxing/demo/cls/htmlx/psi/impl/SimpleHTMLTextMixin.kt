package me.yiiguxing.demo.cls.htmlx.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

open class SimpleHTMLTextMixin(node: ASTNode) : SimpleHTMLElementBase(node), PsiLanguageInjectionHost {

    override fun isValidHost(): Boolean {
        if (firstChild == null || firstChild !== lastChild) {
            return false
        }

        return firstChild.elementType.let {
            it == SimpleHTMLTypes.STYLE_CODE || it == SimpleHTMLTypes.SCRIPT_CODE
        }
    }

    override fun updateText(text: String): PsiLanguageInjectionHost = apply {
        (firstChild as LeafPsiElement).replaceWithText(text)
    }

    override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost> {
        return LiteralTextEscaper.createSimple(this)
    }
}
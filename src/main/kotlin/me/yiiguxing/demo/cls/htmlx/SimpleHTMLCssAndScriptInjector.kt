package me.yiiguxing.demo.cls.htmlx

import com.intellij.lang.Language
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import com.intellij.psi.templateLanguages.OuterLanguageElement
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLText
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class SimpleHTMLCssAndScriptInjector : MultiHostInjector {

    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context !is SimpleHTMLText || !context.isValid || !context.isValidHost) {
            return
        }

        val child = context.firstChild
        val language: Language = when (child.elementType) {
            SimpleHTMLTypes.STYLE_CODE -> cssLanguage
            SimpleHTMLTypes.SCRIPT_CODE -> {
                if (context.isJavaScriptCode()) javaScriptLanguage else null //TODO Other languages?
            }
            else -> null
        } ?: return

        if (child is OuterLanguageElement) {
            return
        }

        registrar
            .startInjecting(language)
            .addPlace(null, null, context, child.textRangeInParent)
            .doneInjecting()
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> {
        return listOf(SimpleHTMLText::class.java)
    }

    companion object {
        private val cssLanguage: Language? by lazy { Language.findLanguageByID("CSS") }
        private val javaScriptLanguage: Language? by lazy { Language.findLanguageByID("JavaScript") }

        private fun SimpleHTMLText.isJavaScriptCode(): Boolean {
            val tag = parent as? SimpleHTMLTag ?: return false
            if (!tag.name.equals("script", true)) {
                return false
            }

            val type = tag.getAttributeValue("type")
            return type.isNullOrEmpty()
                    || type.contains("JavaScript", true)
                    || type.contains("jscript", true)
        }
    }

}
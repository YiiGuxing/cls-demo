package me.yiiguxing.demo.cls.htmlx.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import me.yiiguxing.demo.cls.htmlx.HTML_TAGS
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class TagNameCompletionContributor : CompletionContributor() {

    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(SimpleHTMLTypes.START_TAG_NAME),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    for (i in HTML_TAGS.indices) {
                        result.addElement(LookupElementBuilder.create(HTML_TAGS[i]));
                    }
                }
            }
        )
    }

}
package me.yiiguxing.demo.cls.htmlx.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.Language
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.folding.LanguageFolding
import com.intellij.openapi.editor.Document
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilCore
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLLanguage
import me.yiiguxing.demo.cls.htmlx.psi.*

class SimpleHTMLFoldingBuilder : CustomFoldingBuilder(), DumbAware {

    override fun buildLanguageFoldRegions(
        descriptors: MutableList<FoldingDescriptor>,
        root: PsiElement,
        document: Document,
        quick: Boolean
    ) {
        val simpleHTMLDocument = when (root) {
            is SimpleHTMLFile -> root.document
            is SimpleHTMLDocument -> root
            else -> PsiTreeUtil.getChildOfType(root, SimpleHTMLDocument::class.java)
        } ?: return

        simpleHTMLDocument.buildFoldRegions(descriptors, document)
    }

    override fun getLanguagePlaceholderText(node: ASTNode, range: TextRange): String? {
        return when (node.psi) {
            is SimpleHTMLTag,
            is SimpleHTMLComment,
            is SimpleHTMLAttribute -> "..."
            else -> null
        }
    }

    override fun isRegionCollapsedByDefault(node: ASTNode): Boolean {
        return node.elementType == SimpleHTMLTypes.COMMENT
    }

    companion object {
        private fun PsiElement.buildFoldRegions(descriptors: MutableList<FoldingDescriptor>, document: Document) {
            ProgressManager.checkCanceled()

            when (this) {
                is SimpleHTMLDoctype, is SimpleHTMLText -> return
                is SimpleHTMLTag -> addToFold(descriptors, getFoldRange(), document)
                is SimpleHTMLComment -> {
                    addToFold(descriptors, getFoldRange(), document)
                    return
                }
                is SimpleHTMLAttribute -> {
                    addToFold(descriptors, getFoldRange(), document)
                    return
                }
                else -> if (language !== SimpleHTMLLanguage) {
                    buildFoldRegionsForOtherLanguage(descriptors, document)
                    return
                }
            }

            var child = firstChild
            while (child != null) {
                child.buildFoldRegions(descriptors, document)
                child = child.nextSibling
            }
        }

        private fun SimpleHTMLTag.getFoldRange(): TextRange {
            val startOffset = tagNameElement?.textRange?.endOffset ?: return TextRange.EMPTY_RANGE
            val endOffset = lastChild
                ?.takeIf {
                    it.elementType == SimpleHTMLTypes.TAG_END || it.elementType == SimpleHTMLTypes.EMPTY_TAG_END
                }
                ?.textRange
                ?.startOffset
                ?: (textRange.endOffset - 1)

            return TextRange(startOffset, endOffset)
        }

        private fun SimpleHTMLAttribute.getFoldRange(): TextRange {
            val attributeValue = attributeValue ?: return TextRange.EMPTY_RANGE
            val startOffset = attributeValue
                .firstChild
                ?.takeIf {
                    it.elementType == SimpleHTMLTypes.ATTRIBUTE_VALUE_DEFINER_SQ
                            || it.elementType == SimpleHTMLTypes.ATTRIBUTE_VALUE_DEFINER_DQ
                }
                ?.textRange
                ?.endOffset
                ?: return TextRange.EMPTY_RANGE
            val endOffset = attributeValue
                .lastChild
                ?.takeIf {
                    it.elementType == SimpleHTMLTypes.ATTRIBUTE_VALUE_DEFINER_SQ
                            || it.elementType == SimpleHTMLTypes.ATTRIBUTE_VALUE_DEFINER_DQ
                }
                ?.textRange
                ?.startOffset
                ?: (textRange.endOffset - 1)

            return TextRange(startOffset, endOffset)
        }

        private fun SimpleHTMLComment.getFoldRange(): TextRange {
            val startOffset = firstChild.textRange.endOffset
            val endOffset = lastChild
                ?.takeIf { it.elementType == SimpleHTMLTypes.COMMENT_END }
                ?.textRange
                ?.startOffset
                ?: (textRange.endOffset - 1)
            return TextRange(startOffset, endOffset)
        }

        private fun PsiElement.addToFold(
            descriptors: MutableList<FoldingDescriptor>,
            range: TextRange,
            document: Document
        ) {
            PsiUtilCore.ensureValid(this)
            if (range.isEmpty
                || range.startOffset < 0
                || range.endOffset > containingFile.textRange.endOffset
                || range.endOffset > document.textLength
            ) {
                return
            }
            val startLine = document.getLineNumber(range.startOffset)
            val endLine = document.getLineNumber(range.endOffset)
            if (startLine < endLine) {
                descriptors.add(FoldingDescriptor(this, range))
            }
        }

        private fun PsiElement.buildFoldRegionsForOtherLanguage(
            descriptors: MutableList<FoldingDescriptor>,
            document: Document
        ) {
            if (language === Language.ANY) {
                return
            }

            LanguageFolding.INSTANCE.forLanguage(language)?.let { foldingBuilder ->
                val foldingDescriptors = foldingBuilder.buildFoldRegions(node, document)
                descriptors.addAll(foldingDescriptors)
            }
        }
    }
}
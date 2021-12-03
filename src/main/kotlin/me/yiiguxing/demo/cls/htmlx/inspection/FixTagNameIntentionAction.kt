package me.yiiguxing.demo.cls.htmlx.inspection

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class FixTagNameIntentionAction(
    private val targetName: String,
    private val sourceName: String
) : IntentionAction {

    override fun getFamilyName(): String = text

    override fun getText(): String {
        return "Rename end tag $sourceName to $targetName"
    }

    override fun startInWriteAction(): Boolean = true

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean = true

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val offset = editor.caretModel.offset
        var psiElement = file.findElementAt(offset)
        if (psiElement is PsiWhiteSpace) {
            psiElement = PsiTreeUtil.prevLeaf(psiElement)
        }

        val startTag = psiElement
            ?.takeIf { it.elementType == SimpleHTMLTypes.START_TAG_NAME && it.text == targetName }
            ?: return
        val tag = startTag.parent as? SimpleHTMLNotEmptyTag ?: return
        val endTag = tag.endTagNameElement ?: return
        if (endTag.text != targetName) {
            val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return
            val textRange: TextRange = endTag.textRange
            document.replaceString(textRange.startOffset, textRange.endOffset, targetName)
        }
    }

}
package me.yiiguxing.demo.cls.htmlx.completion

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class SimpleHTMLTypedHandlerDelegate : TypedHandlerDelegate() {

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        if (c == '>' && file is SimpleHTMLFile) {
            handleTyped(project, editor, file)
        }

        return super.charTyped(c, project, editor, file)
    }

    private fun handleTyped(project: Project, editor: Editor, file: PsiFile) {
        val document = editor.document
        PsiDocumentManager.getInstance(project).commitDocument(document)

        val offset = editor.caretModel.offset
        val element = file.findElementAt(offset - 1) ?: return
        if (element.elementType != SimpleHTMLTypes.TAG_END) {
            return
        }

        val parent = element.parent
        if (parent !is SimpleHTMLNotEmptyTag || parent.lastChild === element) {
            return
        }

        val startTagName = parent.startTagNameElement?.text ?: return
        document.insertString(offset, "</$startTagName>")
    }

}
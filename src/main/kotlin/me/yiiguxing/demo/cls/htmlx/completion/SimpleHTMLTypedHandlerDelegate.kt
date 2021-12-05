package me.yiiguxing.demo.cls.htmlx.completion

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLNotEmptyTag
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLText
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes

class SimpleHTMLTypedHandlerDelegate : TypedHandlerDelegate() {

    override fun beforeCharTyped(c: Char, project: Project, editor: Editor, file: PsiFile, fileType: FileType): Result {
        if (c == '/' && file is SimpleHTMLFile) {
            handleBeforeSlashTyped(project, editor, file)
        }

        return super.beforeCharTyped(c, project, editor, file, fileType)
    }

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        if (file is SimpleHTMLFile) {
            when (c) {
                '>' -> handleGtTyped(project, editor, file)
                '/' -> handleSlashTyped(project, editor, file)
                '=' -> handleEqTyped(project, editor, file)
            }
        }

        return super.charTyped(c, project, editor, file)
    }

    private fun handleGtTyped(project: Project, editor: Editor, file: PsiFile) {
        val element = findElement(project, editor, file) ?: return
        if (element.elementType != SimpleHTMLTypes.TAG_END) {
            return
        }

        val parent = element.parent
        if (parent !is SimpleHTMLNotEmptyTag || parent.lastChild === element) {
            return
        }

        val startTagName = parent.startTagNameElement?.text ?: return
        if (!startTagName.equals(parent.endTagNameElement?.text, true)) {
            editor.document.insertString(editor.caretModel.offset, "</$startTagName>")
        }
    }

    private fun handleBeforeSlashTyped(project: Project, editor: Editor, file: PsiFile) {
        val element = findElement(project, editor, file, false)
        if (element == null || element.elementType != SimpleHTMLTypes.TAG_END) {
            return
        }

        val tag = element.parent as? SimpleHTMLNotEmptyTag ?: return
        if (element.nextSibling?.elementType == SimpleHTMLTypes.END_TAG_START) {
            val tagName = tag.name
            val endTagName = tag.endTagNameElement?.text
            if (endTagName == null || tagName.equals(endTagName, true)) {
                editor.document.replaceString(editor.caretModel.offset + 1, tag.textRange.endOffset, "")
            }
        }
    }

    private fun handleSlashTyped(project: Project, editor: Editor, file: PsiFile) {
        val element = findElement(project, editor, file) ?: return
        if (element.elementType == SimpleHTMLTypes.ROW_TEXT && element.textLength == 1) {
            val previousSibling = (element.parent as? SimpleHTMLText)
                ?.let { PsiTreeUtil.skipWhitespacesBackward(it) }
                ?: return
            if (previousSibling.elementType == SimpleHTMLTypes.EMPTY_TAG
                && previousSibling.lastChild?.elementType != SimpleHTMLTypes.EMPTY_TAG_END
            ) {
                editor.document.insertString(editor.caretModel.offset, ">")
            }
        } else if (element.elementType == SimpleHTMLTypes.END_TAG_START) {
            val tag = element.parent as? SimpleHTMLNotEmptyTag ?: return
            if (!tag.name.equals(tag.endTagNameElement?.text, false)) {
                editor.document.insertString(editor.caretModel.offset, "${tag.name}>")
            }
        }
    }

    private fun handleEqTyped(project: Project, editor: Editor, file: PsiFile) {
        val element = findElement(project, editor, file) ?: return
        if (element.elementType == SimpleHTMLTypes.ATTRIBUTE_ASSIGN) {
            val caretModel = editor.caretModel
            editor.document.insertString(caretModel.offset, "\"\"")
            caretModel.moveToOffset(caretModel.offset + 1)
        }
    }

    private fun findElement(project: Project, editor: Editor, file: PsiFile, afterType: Boolean = true): PsiElement? {
        PsiDocumentManager.getInstance(project).commitDocument(editor.document)
        var offset = editor.caretModel.offset
        if (afterType) {
            offset--
        }
        return file.findElementAt(offset)
    }

}
package me.yiiguxing.demo.cls.htmlx.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLElement

abstract class SimpleHTMLCommentBase(node: ASTNode) : SimpleHTMLElementBase(node), SimpleHTMLElement, PsiComment {

    override fun getTokenType(): IElementType = node.elementType

}
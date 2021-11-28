package me.yiiguxing.demo.cls.htmlx.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLElement

open class SimpleHTMLElementBase(node: ASTNode) : ASTWrapperPsiElement(node), SimpleHTMLElement
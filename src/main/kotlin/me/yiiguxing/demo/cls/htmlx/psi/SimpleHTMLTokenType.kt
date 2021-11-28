package me.yiiguxing.demo.cls.htmlx.psi

import com.intellij.psi.tree.IElementType
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLLanguage
import org.jetbrains.annotations.NonNls

class SimpleHTMLTokenType(@NonNls debugName: String) : IElementType(debugName, SimpleHTMLLanguage) {

    override fun toString(): String = "SimpleHTMLTokenType.${super.toString()}"

}
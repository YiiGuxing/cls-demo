package me.yiiguxing.demo.cls.htmlx.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLLanguage
import me.yiiguxing.demo.cls.htmlx.psi.SimpleHTMLTypes.*

class SimpleHTMLParserDefinition : ParserDefinition {

    override fun createLexer(project: Project): Lexer = SimpleHTMLLexer()

    override fun createParser(project: Project): PsiParser = SimpleHTMLParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet = STRING_LITERALS

    override fun createElement(node: ASTNode): PsiElement = Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = SimpleHTMLFile(viewProvider)

    companion object {
        val FILE = IFileElementType(SimpleHTMLLanguage)

        val STRING_LITERALS = TokenSet.create(TEXT, STYLE_CODE, SCRIPT_CODE, COMMENT_TEXT, ROW_TEXT)
    }

}
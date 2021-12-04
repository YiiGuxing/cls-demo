package me.yiiguxing.demo.cls.htmlx.psi

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import me.yiiguxing.demo.cls.htmlx.SIMPLE_HTML_FILE_EXTENSION
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFile
import me.yiiguxing.demo.cls.htmlx.SimpleHTMLFileType


@Service
class SimpleHTMLElementFactory(private val project: Project) {

    fun createTagElement(name: String): SimpleHTMLNotEmptyTag {
        return createSimpleHTMLFileFromText("<$name></$name>").document?.rootTag as SimpleHTMLNotEmptyTag
    }

    fun createAttributeNameElement(name: String): PsiElement {
        val dummyTag = createSimpleHTMLFileFromText("<dummy $name />").document?.rootTag as SimpleHTMLEmptyTag
        return dummyTag.attributeList.first().attributeNameElement
    }

    private fun createSimpleHTMLFileFromText(text: String): SimpleHTMLFile {
        return PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.$SIMPLE_HTML_FILE_EXTENSION", SimpleHTMLFileType, text) as SimpleHTMLFile
    }

    companion object {
        fun getInstance(project: Project): SimpleHTMLElementFactory {
            return project.getService(SimpleHTMLElementFactory::class.java)
        }
    }

}
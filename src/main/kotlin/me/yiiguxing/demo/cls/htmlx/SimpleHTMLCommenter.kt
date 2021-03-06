package me.yiiguxing.demo.cls.htmlx

import com.intellij.lang.Commenter

class SimpleHTMLCommenter : Commenter {

    override fun getLineCommentPrefix(): String? = null

    override fun getBlockCommentPrefix(): String = "<!--"

    override fun getBlockCommentSuffix(): String = "-->"

    override fun getCommentedBlockCommentPrefix(): String = "<!--"

    override fun getCommentedBlockCommentSuffix(): String = "-->"
}
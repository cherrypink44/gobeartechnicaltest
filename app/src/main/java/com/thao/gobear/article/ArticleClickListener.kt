package com.thao.gobear.article

import com.thao.gobear.article.model.ArticleModel

public interface ArticleClickListener {
    fun onClick(article: ArticleModel)
}
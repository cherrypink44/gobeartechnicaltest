package com.thao.gobear.article.rss

import android.os.AsyncTask
import com.thao.gobear.article.model.ArticleModel
import java.net.URL

class RssReader(
    private var url: String,
    private var callback: RssCallback
) : AsyncTask<Void, Void, ArrayList<ArticleModel>>() {
    override fun doInBackground(vararg params: Void?): ArrayList<ArticleModel>? {
        try {
            val inputStream = URL(url).openConnection().getInputStream()
            val document = XMLUtil.getDocumentFromStream(inputStream)
            document?.let {
                return XMLUtil.getArticleList(it)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: ArrayList<ArticleModel>?) {
        super.onPostExecute(result)
        result?.let {
            callback.onSuccess(it)
        }
    }

    interface RssCallback {
        fun onSuccess(articleList: ArrayList<ArticleModel>)
        fun onFail(e: Exception)
    }
}
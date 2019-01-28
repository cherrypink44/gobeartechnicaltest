package com.thao.gobear

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thao.gobear.article.ArticleClickListener
import com.thao.gobear.article.adapter.ArticleAdapter
import com.thao.gobear.article.model.ArticleModel
import com.thao.gobear.article.rss.RssReader
import com.thao.gobear.util.SharePrefUtils
import kotlinx.android.synthetic.main.activity_acrticle_list.*

class ArticleListActivity : AppCompatActivity(), RssReader.RssCallback, SwipeRefreshLayout.OnRefreshListener,
    ArticleClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrticle_list)
        swipeRefreshLayout.setOnRefreshListener(this)
        loadRss()
        list_article.adapter = ArticleAdapter(ArrayList(), this)

        txtLogout.setOnClickListener {
            SharePrefUtils.setBoolean(this, SharePrefUtils.Key.REMEMBER, false)
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onSuccess(articleList: ArrayList<ArticleModel>) {
        val articleAdapter = ArticleAdapter(articleList, this)
        list_article.adapter = articleAdapter
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onFail(e: Exception) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        clearData()
        loadRss()
    }

    private fun loadRss() {
        RssReader(getString(R.string.rss_host), this).execute(null as Void?)
    }

    private fun clearData() {
        (list_article.adapter as ArticleAdapter).clear()
    }

    override fun onClick(article: ArticleModel) {
        val intent = Intent(this, NewsArticleDetailActivity::class.java)
        val bundle = Bundle().apply {
            putSerializable(NewsArticleDetailActivity.LINK, article.link)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
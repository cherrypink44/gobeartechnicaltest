package com.thao.gobear

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_news_article_detail.*

class NewsArticleDetailActivity : AppCompatActivity() {
    companion object {
        var LINK = "LINK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_detail)
        val link = intent?.extras?.getString(LINK)
        webView.loadUrl(link)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                progress_circular.visibility = View.GONE
            }
        }
    }
}
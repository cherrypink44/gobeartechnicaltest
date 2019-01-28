package com.thao.gobear

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thao.gobear.util.SharePrefUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isLaunched = SharePrefUtils.getBoolean(this, SharePrefUtils.Key.LAUNCHED)
        if (isLaunched) {
            val isRemember = SharePrefUtils.getBoolean(this, SharePrefUtils.Key.REMEMBER)
            if (isRemember) {
                showArticleList()
            } else {
                showLogin();
            }
        } else {
            showWalkthrough()
        }
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showArticleList() {
        val intent = Intent(this, ArticleListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showWalkthrough() {
        val intent = Intent(this, WalkthroughActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

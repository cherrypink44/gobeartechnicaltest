package com.thao.gobear

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.thao.gobear.util.SharePrefUtils
import com.thao.gobear.walkthrough.adapter.IntroAdapter
import com.thao.gobear.walkthrough.fragment.WalkThroughFragment
import kotlinx.android.synthetic.main.activity_walk_through.*

class WalkthroughActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)
        initSlider()
        txtSkip.setOnClickListener { showLogin() }
        btnDone.setOnClickListener { showLogin() }
    }

    private fun initSlider() {
        val fragmentList =
            listOf<Fragment>(WalkThroughFragment(), WalkThroughFragment(), WalkThroughFragment())

        val introAdapter = IntroAdapter(fragmentList, supportFragmentManager)
        viewPager.adapter = introAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    introAdapter.count - 1 -> {
                        txtSkip.visibility = View.GONE
                        btnDone.visibility = View.VISIBLE
                    }
                    else -> {
                        txtSkip.visibility = View.VISIBLE
                        btnDone.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun showLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        SharePrefUtils.setBoolean(this, SharePrefUtils.Key.LAUNCHED, true)
    }
}
package com.thao.gobear.walkthrough.adapter

class IntroAdapter(var fragmentList: List<androidx.fragment.app.Fragment>, fm: androidx.fragment.app.FragmentManager) :
    androidx.fragment.app.FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}

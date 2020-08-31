package com.aiwadev.viewpagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val tabLabels = arrayListOf<String>("Tab One", "Tab Two")

        prepareViewPager(viewPager, tabLabels)

        tabLayout.setupWithViewPager(viewPager, true)
    }

    private fun prepareViewPager(viewPager: ViewPager?, tabLabels: List<String>) {

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)

        val firstFragment = FirstFragment.newInstance()
        val secondFragment = SecondFragment.newInstance()

        pagerAdapter.addFragment(firstFragment, tabLabels[0])
        pagerAdapter.addFragment(secondFragment, tabLabels[1])

        viewPager?.adapter = pagerAdapter
    }

    class ViewPagerAdapter(supportFragmentManager: FragmentManager)
        : FragmentPagerAdapter(supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val arrayList = arrayListOf<String>()
        private val fragmentList = arrayListOf<Fragment>()

        fun addFragment(fragment: Fragment, title: String) {
            arrayList.add(title)
            fragmentList.add(fragment)
        }

        override fun getCount(): Int = fragmentList.size

        override fun getItem(position: Int): Fragment = fragmentList[position]

        override fun getPageTitle(position: Int): CharSequence? {
            return arrayList[position]
        }
    }
}
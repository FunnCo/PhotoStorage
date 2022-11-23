package com.funnco.photostorage.activity.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.funnco.photostorage.activity.main.fragment.all.AllPhotoFragment
import com.funnco.photostorage.activity.main.fragment.my.MyPhotoFragment

class ScreenPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->  AllPhotoFragment()
            else -> MyPhotoFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "All photo"
            else -> "My photo"
        }
    }
}
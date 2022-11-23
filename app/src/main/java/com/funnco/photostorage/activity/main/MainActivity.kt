package com.funnco.photostorage.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.funnco.photostorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.activityMainViewpager.adapter = ScreenPagerAdapter(supportFragmentManager)
        binding.activityMainTablayout.setupWithViewPager(binding.activityMainViewpager)
    }
}
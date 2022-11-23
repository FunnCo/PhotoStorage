package com.funnco.photostorage.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.funnco.photostorage.R
import com.funnco.photostorage.activity.main.MainActivity
import com.funnco.photostorage.common.DatabaseHandler
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },2500)


        val prefs = getSharedPreferences("UUID", MODE_PRIVATE)

        if(!prefs.contains("uuid")){
            prefs.edit().putString("uuid", Random.nextLong(1000000000000000, 99999999999999999).toString()).apply()
        }
        Log.i("Splash", "Current uuid: ${prefs.getString("uuid", "-1")!!}")
        DatabaseHandler.readAllImages(prefs.getString("uuid", "-1")!!)
    }
}
package com.example.newsapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
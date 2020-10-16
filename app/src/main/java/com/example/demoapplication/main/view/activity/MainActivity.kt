package com.example.demoapplication.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoapplication.R
import com.example.demoapplication.main.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}

package com.hajarslamah.fragapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment=supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(currentFragment==null){
            val fragment =Fragment_Student.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()

        }

    }
    }


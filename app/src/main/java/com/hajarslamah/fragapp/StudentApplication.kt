package com.hajarslamah.fragapp

import android.app.Application

class StudentApplication :Application(){
    override fun onCreate() {
        super.onCreate()
       StudentRepository.initialize(this)
    }
}
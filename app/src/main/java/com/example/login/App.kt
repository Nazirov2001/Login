package com.example.login

import android.app.Application
import com.example.login.utils.Prefs

class App : Application() {
    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Prefs.init(this)
    }
}
package com.example.moviesbydany

import android.app.Application
import android.content.Context

import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {
    companion object;

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

}
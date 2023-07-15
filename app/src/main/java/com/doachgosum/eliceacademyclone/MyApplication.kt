package com.doachgosum.eliceacademyclone

import android.app.Application
import com.doachgosum.eliceacademyclone.di.AppContainer

class MyApplication: Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
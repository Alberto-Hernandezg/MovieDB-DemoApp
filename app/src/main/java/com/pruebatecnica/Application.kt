package com.pruebatecnica

import android.app.Application
import com.pruebatecnica.managers.KoinManager

class Application : Application(), KoinManager {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}

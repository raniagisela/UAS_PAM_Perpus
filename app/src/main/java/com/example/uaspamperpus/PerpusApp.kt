package com.example.uaspamperpus

import android.app.Application
import com.example.uaspamperpus.repositori.ContainerApp
import com.example.uaspamperpus.repositori.ContainerDataApp

class PerpusApp : Application(){
    lateinit var container : ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}
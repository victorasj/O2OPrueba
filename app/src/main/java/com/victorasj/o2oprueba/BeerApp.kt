package com.victorasj.o2oprueba

import android.app.Application

class BeerApp : Application() {

    override fun onCreate() {
        initServiceLocator()
        super.onCreate()
    }

}
package com.example.searchrecipe

import android.app.Application
import com.example.searchrecipe.di.component.AppComponent
import com.example.searchrecipe.di.component.DaggerAppComponent

class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent
        return DaggerAppComponent.create()
    }

}


package com.demo.mvvmcleanarchitecture

import android.app.Application
import com.demo.mvvmcleanarchitecture.common.di.component.AppComponent
import com.demo.mvvmcleanarchitecture.common.di.component.DaggerAppComponent
import com.demo.mvvmcleanarchitecture.common.di.module.AppModule
import com.facebook.drawee.backends.pipeline.Fresco

class DemoApplication : Application() {
    companion object {
        lateinit var appComponents: AppComponent
        lateinit var instance: DemoApplication

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponents = initDI()
        Fresco.initialize(this)

    }


    private fun initDI(): AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

}
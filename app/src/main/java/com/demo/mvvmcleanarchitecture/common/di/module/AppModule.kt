/*
 * *
 *  * Created by Ahlem Jarrar on 1/20/20 2:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/20/20 2:58 PM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.proxym.quarantracking.common.sharedpreference.AppSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appContext : Application) {


    @Provides
    fun provideApplication() : Context = appContext.applicationContext



    @Provides
    @Singleton
    fun providesSharePrefernces() : AppSharedPreferences =  AppSharedPreferences(appContext.applicationContext)

    @Provides
    fun providesGson() : Gson {
            val gsonBuilder =  GsonBuilder()
            gsonBuilder.excludeFieldsWithoutExposeAnnotation()
            gsonBuilder.setPrettyPrinting()
            return gsonBuilder.create()
        }

}
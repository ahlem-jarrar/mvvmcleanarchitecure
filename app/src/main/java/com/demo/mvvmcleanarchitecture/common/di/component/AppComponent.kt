/*
 * *
 *  * Created by Ahlem Jarrar on 1/21/20 12:03 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/21/20 11:54 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common.di.component

import android.content.Context
import com.demo.mvvmcleanarchitecture.common.di.module.AppModule
import com.google.gson.Gson
import com.proxym.nep.di.viewmodels.DaggerViewModelFactory
import com.demo.mvvmcleanarchitecture.common.di.module.CoroutinesModule
import com.proxym.quarantracking.common.di.module.NetworkModule
import com.demo.mvvmcleanarchitecture.common.di.module.RepositoriesModule
import com.proxym.quarantracking.common.di.viewmodels.ViewModelModule
import com.proxym.quarantracking.common.sharedpreference.AppSharedPreferences
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, CoroutinesModule::class, NetworkModule::class, RepositoriesModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun context(): Context
    fun provideDaggerViewModelFactory() : DaggerViewModelFactory
    fun provideGson() : Gson
    fun providesAppSharedPref(): AppSharedPreferences

}
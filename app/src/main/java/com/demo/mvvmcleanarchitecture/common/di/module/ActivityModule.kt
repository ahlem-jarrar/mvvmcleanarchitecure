/*
 * *
 *  * Created by Ahlem Jarrar on 1/29/20 11:02 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 11:02 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common.di.module

import android.content.Context
import com.demo.mvvmcleanarchitecture.DemoApplication
import com.demo.mvvmcleanarchitecture.common.BaseActivity

import com.proxym.quarantracking.common.router.AppRouter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private val activity : BaseActivity) {

    @Provides
    fun providesActivityConetxt() : Context = activity.application


    @Provides
    fun providesActivity() : BaseActivity { return activity}


    @Provides
    fun providesRouterComponent() : AppRouter = AppRouter(activity, DemoApplication.appComponents.provideGson())
}
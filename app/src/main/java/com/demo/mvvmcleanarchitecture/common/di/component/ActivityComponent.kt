/*
 * *
 *  * Created by Ahlem Jarrar on 1/29/20 11:02 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/29/20 11:02 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common.di.component

import com.demo.mvvmcleanarchitecture.common.di.annotations.ActivityScope
import com.demo.mvvmcleanarchitecture.common.di.module.ActivityModule
import com.proxym.quarantracking.common.router.AppRouter
import com.proxym.quarantracking.common.sharedpreference.AppSharedPreferences
import com.demo.mvvmcleanarchitecture.common.BaseActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun providesAppSharedPref(): AppSharedPreferences
    fun appRouter(): AppRouter
}
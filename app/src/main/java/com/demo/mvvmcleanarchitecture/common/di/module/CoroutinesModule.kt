/*
 * *
 *  * Created by Ahlem Jarrar on 1/21/20 11:54 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/15/20 9:06 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common.di.module


import com.demo.data.common.DefaultDispatcher
import com.demo.data.common.IoDispatcher
import com.demo.data.common.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object CoroutinesModule {

    @DefaultDispatcher
    @JvmStatic
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @JvmStatic
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @JvmStatic
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

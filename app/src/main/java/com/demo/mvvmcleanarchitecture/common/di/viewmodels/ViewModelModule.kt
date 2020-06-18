/*
 * *
 *  * Created by Ahlem Jarrar on 1/21/20 11:59 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/15/20 9:06 AM
 *
 *
 */

package com.proxym.quarantracking.common.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proxym.nep.di.viewmodels.DaggerViewModelFactory
import com.demo.mvvmcleanarchitecture.common.di.annotations.ViewModelKey
import com.proxym.quarantracking.ui.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [DaggerViewModelFactory].
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    abstract fun bindLauncherViewModel(launcherViewModel: LauncherViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}
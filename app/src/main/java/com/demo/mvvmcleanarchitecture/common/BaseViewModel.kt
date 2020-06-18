package com.demo.mvvmcleanarchitecture.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proxym.quarantracking.common.router.AppRouter
import com.proxym.quarantracking.common.sharedpreference.AppSharedPreferences


open class BaseViewModel : ViewModel() {
    var toggleLoading: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var appRouter: AppRouter
    lateinit var appSharedPref: AppSharedPreferences




    open fun onPause() {
        toggleLoading.value = false
    }

}
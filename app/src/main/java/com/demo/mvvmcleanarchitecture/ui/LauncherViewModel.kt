

package com.proxym.quarantracking.ui

import androidx.lifecycle.MutableLiveData
import com.demo.mvvmcleanarchitecture.common.BaseViewModel

import javax.inject.Inject

class LauncherViewModel @Inject constructor() :  BaseViewModel() {
    var result = MutableLiveData<Boolean>()

    fun redirectUser() {

    }

}
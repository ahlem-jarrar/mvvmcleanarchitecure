/*
 * *
 *  * Created by Yosra brahem on 3/9/20 12:22 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/9/20 12:22 PM
 *
 */

package com.proxym.quarantracking.common.router

import com.google.gson.Gson
import com.demo.mvvmcleanarchitecture.common.BaseActivity

import com.demo.mvvmcleanarchitecture.common.di.annotations.ActivityScope
import javax.inject.Inject

@ActivityScope
class AppRouter @Inject constructor(private val activity : BaseActivity, private val gson : Gson) {

    companion object{
        const val ARG_MESSAGE_ID = "ARG_MESSAGE_ID"
        const val USER_ID = "USER_ID"
    }

    fun goBack(){
        activity.finish()
    }

}
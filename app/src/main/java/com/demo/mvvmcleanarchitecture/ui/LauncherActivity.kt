/*
 * *
 *  * Created by Ahlem Jarrar on 2/27/20 4:55 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/27/20 4:53 PM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.ui

import android.content.Intent
import android.os.Bundle
import com.demo.mvvmcleanarchitecture.R
import com.demo.mvvmcleanarchitecture.common.BaseVMActivity
import com.proxym.quarantracking.ui.LauncherViewModel
import timber.log.Timber

class LauncherActivity : BaseVMActivity<LauncherViewModel>(LauncherViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity()::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_launcher
    }

    override fun initView() {
        viewModel.redirectUser()
    }


    override fun initData() {
    }


    override fun startObserve() {

    }
}



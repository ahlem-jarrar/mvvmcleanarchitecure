/*
 * *
 *  * Created by Ahlem Jarrar on 2/27/20 4:55 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/27/20 4:53 PM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.ui


import com.demo.mvvmcleanarchitecture.R
import com.demo.mvvmcleanarchitecture.common.BaseVMActivity
import com.proxym.quarantracking.ui.LauncherViewModel


class LauncherActivity : BaseVMActivity<LauncherViewModel>(LauncherViewModel::class.java) {


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



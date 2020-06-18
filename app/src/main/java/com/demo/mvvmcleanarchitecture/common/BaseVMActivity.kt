/*
 * *
 *  * Created by Yosra brahem on 3/9/20 9:46 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/9/20 9:43 AM
 *
 */

package com.demo.mvvmcleanarchitecture.common


import android.os.Bundle
import androidx.lifecycle.Observer
import com.proxym.quarantracking.common.extension.viewModelProvider


abstract class BaseVMActivity<VM : BaseViewModel>(private val modelClass: Class<VM>) : BaseActivity() {



    abstract fun startObserve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.appRouter = appRouter
        viewModel.appSharedPref = appSharedPref

        startObserve()
        initData()
        initView()
        initToggle()


    }

    protected val viewModel: VM by lazy { viewModelProvider(this.viewModelFactory, modelClass.kotlin) }



    private fun initToggle() {
        viewModel.toggleLoading.observe(this, Observer { toggleLoading(it!!) })
    }

}
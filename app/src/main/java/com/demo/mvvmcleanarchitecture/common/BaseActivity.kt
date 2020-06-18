

package com.demo.mvvmcleanarchitecture.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demo.mvvmcleanarchitecture.DemoApplication
import com.demo.mvvmcleanarchitecture.R
import com.demo.mvvmcleanarchitecture.common.di.module.ActivityModule
import com.proxym.nep.di.viewmodels.DaggerViewModelFactory
import com.demo.mvvmcleanarchitecture.common.di.component.ActivityComponent
import com.demo.mvvmcleanarchitecture.common.di.component.DaggerActivityComponent
import com.demo.mvvmcleanarchitecture.utils.connection.ConnectionLiveData
import com.proxym.quarantracking.common.extension.toast
import com.proxym.quarantracking.common.router.AppRouter
import com.proxym.quarantracking.common.sharedpreference.AppSharedPreferences

import javax.inject.Inject

abstract class BaseActivity  : AppCompatActivity(){

    protected val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(
        ActivityModule(this)
    ).appComponent(DemoApplication.appComponents).build()  }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    @Inject
    lateinit var appRouter: AppRouter
    @Inject
    lateinit var appSharedPref : AppSharedPreferences



    private lateinit var container: ViewGroup
    private lateinit var loading: View


    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setTransculentStatusBar()
        initLoading()
        initConnexionListener()
    }

    private fun initConnexionListener(){
        ConnectionLiveData(this).observe(this, androidx.lifecycle.Observer{
           if (!it) toast("Internet is not available")
        })
    }

    fun replaceFragment(fragment: Fragment, container: Int) {
        val className = fragment.javaClass.name
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment, className)
            .addToBackStack(className)
            .commit()
    }

    private fun setTransculentStatusBar() {
        window.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // to change statusbar's items to dark
        }
    }
    fun replaceFragmentNoAddToBackStack(fragment: Fragment, cont: Int) {
        val className = fragment.javaClass.name
        supportFragmentManager
            .beginTransaction()
            .add(cont, fragment, className)
            .commitAllowingStateLoss()
    }



    private fun initLoading() {
        container = findViewById<View>(android.R.id.content) as ViewGroup
        loading = LayoutInflater.from(this).inflate(R.layout.layout_loading, container, false)
        loading.setOnTouchListener { _, _ -> true }
    }



    fun toggleLoading(show: Boolean) {
        synchronized(DemoApplication.appComponents) {
            if (!isDestroyed) {
                container.removeView(loading)
                if (show) {
                    container.addView(loading)
                }
            }
        }
    }



}
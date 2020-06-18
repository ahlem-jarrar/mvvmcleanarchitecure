package com.demo.mvvmcleanarchitecture.common

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.demo.mvvmcleanarchitecture.common.Constants.CALL_PHONE_PERMISSION
import com.demo.mvvmcleanarchitecture.common.Constants.READ_STORAGE_PERMISSION
import com.proxym.quarantracking.common.extension.viewModelProvider
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


abstract class BaseVMFragment<VM : BaseViewModel>(private val modelClass: Class<VM>) : Fragment(), EasyPermissions.PermissionCallbacks {

    protected lateinit var activity: BaseActivity
    @NonNull
    protected lateinit var mview : View
    protected var permissionGrated: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          mview = inflater.inflate(getLayoutResId(), container, false)
        return mview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.appRouter = activity.appRouter
        viewModel.appSharedPref = activity.appSharedPref
        startObserve()
        initView()
        initData()
        initToggle()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun startObserve()

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()

    protected val viewModel: VM by lazy { viewModelProvider(activity.viewModelFactory, modelClass.kotlin) }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        permissionGrated = true
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        permissionGrated = false
    }


    @AfterPermissionGranted(READ_STORAGE_PERMISSION)
    open fun checkReadStoragePermission() {
        val perms = Manifest.permission.READ_EXTERNAL_STORAGE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !EasyPermissions.hasPermissions(
                activity,
                perms
            )
        ) {
            EasyPermissions.requestPermissions(
                this,
                "Read storage",
                READ_STORAGE_PERMISSION,
                perms
            )
        }
    }

    @AfterPermissionGranted(CALL_PHONE_PERMISSION)
    open fun checkCallPhonePermission() {
        val perms = Manifest.permission.CALL_PHONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !EasyPermissions.hasPermissions(
                activity,
                perms
            )
        ) {
            EasyPermissions.requestPermissions(
                this,
                "Call phone",
                CALL_PHONE_PERMISSION,
                perms
            )
        }
    }


    protected fun toggleLoading(show: Boolean) {
        if (isAdded) {
            activity.toggleLoading(show)
        }
    }

    private fun initToggle() {
        viewModel.toggleLoading.observe(this, Observer { toggleLoading(it!!) })
    }


}
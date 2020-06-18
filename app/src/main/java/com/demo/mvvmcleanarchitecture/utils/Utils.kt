package com.demo.mvvmcleanarchitecture.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.View
import android.widget.EditText
import android.widget.ScrollView
import androidx.annotation.IntDef
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class Utils {

    companion object {
        /**
         * Update app language - already created views needs to be re-created
         *
         * @param context
         * @param locale
         */

        fun getText(data: Any): String {
            var str = ""
            when (data) {
                is TextInputEditText -> str = data.text.toString()
                is EditText -> str = data.text.toString()
                is String -> str = data
            }
            return str
        }

        fun hidKeyBoard(rootView: View, scroll: ScrollView) {
            rootView.viewTreeObserver.addOnGlobalLayoutListener {
                val rec = Rect()
                if (rootView != null) {
                    rootView.getWindowVisibleDisplayFrame(rec)
                    //finding screen height
                    val screenHeight = rootView.rootView.height

                    //finding keyboard height
                    val keypadHeight = screenHeight - rec.bottom

                    if (keypadHeight > screenHeight * 0.15) {
                        scroll.post { scroll.fullScroll(View.FOCUS_UP) }
                    }
                }
            }
        }


        fun blockBackButtonInFragment(view: View) {
            view.isFocusableInTouchMode = true
            view.requestFocus()
            view.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action === KeyEvent.ACTION_DOWN) {
                        if (keyCode == KEYCODE_BACK) {
                            return true
                        }
                    }
                    return false
                }
            })
        }

        fun scrollScreenOnKeyBoardShown(
            rootView: View,
            scrollView: ScrollView,
            scrollPercent: Double
        ) {
            rootView.viewTreeObserver.addOnGlobalLayoutListener {
                val rec = Rect()
                if (rootView != null) {
                    rootView.getWindowVisibleDisplayFrame(rec)
                    //finding screen height
                    val screenHeight = rootView.rootView.height

                    //finding keyboard height
                    val keypadHeight = screenHeight - rec.bottom

                    if (keypadHeight > screenHeight * 0.25) {
                        scrollView.post {
                            scrollView.scrollTo(
                                0,
                                (screenHeight * scrollPercent).toInt()
                            )
                        }

                    }
                }
            }
        }

        fun togglePasswordText(editText: TextInputEditText) {
            if (editText.transformationMethod != null) editText.transformationMethod =
                null
            else editText.transformationMethod =
                PasswordTransformationMethod()
        }


        const val PERMISSION_GRANTED = 0
        const val PERMISSION_DENIED = 1
        const val PERMISSION_BLOCKED_OR_NEVER_ASKED = 2

        @IntDef(value = [PERMISSION_GRANTED, PERMISSION_DENIED, PERMISSION_BLOCKED_OR_NEVER_ASKED])
        @Retention(AnnotationRetention.SOURCE)
        annotation class PermissionStatus


        @PermissionStatus
        fun getPermissionStatus(activity: Activity, androidPermissionName: String): Int {
            return if (ContextCompat.checkSelfPermission(
                    activity,
                    androidPermissionName
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        androidPermissionName
                    )
                ) {
                    PERMISSION_BLOCKED_OR_NEVER_ASKED
                } else PERMISSION_DENIED
            } else PERMISSION_GRANTED
        }

        @Suppress("DEPRECATION")
        fun updateAppLanguage(context: Context, locale: Locale) {
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.setLocale(locale) //= locale
            configuration.setLayoutDirection(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }


    }


}
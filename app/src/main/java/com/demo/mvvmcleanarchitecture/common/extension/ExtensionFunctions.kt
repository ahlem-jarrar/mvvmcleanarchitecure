/*
 * *
 *  * Created by Ahlem Jarrar on 1/21/20 5:39 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/21/20 5:38 PM
 *
 *
 */

package com.proxym.quarantracking.common.extension

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.demo.mvvmcleanarchitecture.common.BaseActivity
import kotlin.reflect.KClass


inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)

 fun < T : ViewModel> BaseActivity.viewModelProvider(factory: ViewModelProvider.Factory, model: KClass<T>):T{
     return ViewModelProvider(this, factory).get(model.java)
 }
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)

fun <T : ViewModel> Fragment.viewModelProvider(factory: ViewModelProvider.Factory, model: KClass<T>): T {
    return ViewModelProvider(this, factory).get(model.java)
}

fun TextInputEditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //TODO
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun RecyclerView.onEndScorll(cb: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val linearLayoutManager = layoutManager as LinearLayoutManager?
            val visibleItemCount = linearLayoutManager?.childCount
            val totalItemCount = linearLayoutManager?.itemCount
            val pastVisibleItems = linearLayoutManager?.findLastCompletelyVisibleItemPosition()

            if (visibleItemCount != null && pastVisibleItems != null && totalItemCount != null) {
                if (((visibleItemCount + pastVisibleItems) >= totalItemCount) && dy > 0) {
                    cb()

                }
            }

        }

    })
}
/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}

/**
 * You may want to apply some common side-effects to your flow to avoid repeating commonly used
 * logic across your app.
 *
 * For e.g. If you want to show/hide progress then use side-effect methods like
 * onStart & onCompletion
 *
 * You can also write common business logic which is applicable to all flows in your application,
 * in this case we are retrying requests 3 times with an exponential delay; if the exception thrown
 * is of type IOException.
 *
 */
/*fun <T : Any> Flow<Result<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < MAX_RETRIES) -> {
                delay(getBackoffDelay(attempt))
                true
            }
            else -> {
                false
            }
        }
    }
        .onStart { emit(Result.Loading(isLoading = true)) }
        .onCompletion { emit(Result.Loading(isLoading = false)) }*/

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun dpToPx(dp: Int, context: Context): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
        context.resources.displayMetrics
    ).toInt()

internal fun Context.getDrawableCompat(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Activity.getScreenHeight(): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}


fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
}

fun String.underlineText() : SpannableString{
  val content =  SpannableString(this)
     content.setSpan( UnderlineSpan(), 0, length, 0)
    return content
}

fun DialogFragment.showOnce(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null) {
        show(manager, tag)
    }
}





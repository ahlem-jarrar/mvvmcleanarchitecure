/*
 * *
 *  * Created by Ahlem Jarrar on 2/6/20 2:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/6/20 2:02 PM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView : View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item : T)
}

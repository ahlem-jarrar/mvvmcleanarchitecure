/*
 * *
 *  * Created by Ahlem Jarrar on 2/18/20 2:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/18/20 8:50 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun io(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.IO) {
        block.invoke(this)
    }
}

suspend fun ui(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main) {
        block.invoke(this)
    }
}
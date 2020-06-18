/*
 * *
 *  * Created by Ahlem Jarrar on 1/21/20 11:39 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/15/20 9:06 AM
 *
 *
 */

package com.demo.data.common

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

/*
 * *
 *  * Created by Ahlem Jarrar on 2/3/20 11:30 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/3/20 11:30 AM
 *
 *
 */

package com.demo.mvvmcleanarchitecture.common


object Constants {

     const val READ_STORAGE_PERMISSION = 101
     const val CALL_PHONE_PERMISSION = 102
     const val MAX_RETRIES = 3L
     private const val INITIAL_BACKOFF = 2000L

     fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)


     const val INBOX_POSITION = 0
     const val SENT_POSITION = 1

}
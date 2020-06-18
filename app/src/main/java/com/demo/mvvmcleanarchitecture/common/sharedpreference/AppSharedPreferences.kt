/*
 * *
 *  * Created by Yosra brahem on 3/6/20 3:41 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 3/6/20 3:41 PM
 *
 */

package com.proxym.quarantracking.common.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import javax.inject.Singleton


@Singleton
class AppSharedPreferences(context: Context) {
    private val gson = GsonBuilder().create()

    companion object {
        const val CURRENT_LANGUAGE = "CURRENT_LANGUAGE"
        const val USER = "USER"
        const val SETTINGS = "SETTNGS"
        const val TOKEN = "TOKEN"
    }


    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            "", Context.MODE_PRIVATE
        )
    }

    /**
     * Saves object into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/
    fun <T> putObject(key: String, y: T) {
        val inString = gson.toJson(y)
        prefs.value.edit().putString(key, inString).apply()
    }
    fun <T> putUser( y: T) {
        val inString = gson.toJson(y)
        prefs.value.edit().putString(USER, inString).apply()
    }

    fun <T> putSettings(y: T) {
        val inString = gson.toJson(y)
        prefs.value.edit().putString(SETTINGS, inString).apply()
    }

    /**
     * Saves collection of objects into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/

    fun <T> getObject(key: String, c: Class<T>): T? {
        val value = prefs.value.getString(key, null)
        if (value != null) {
            return gson.fromJson(value, c)
        } else {
            throw IllegalArgumentException("No object with key: $key was saved")
        }
    }



}
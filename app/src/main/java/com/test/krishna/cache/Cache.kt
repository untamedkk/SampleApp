package com.test.krishna.cache

import android.content.Context
import android.content.SharedPreferences
import com.test.krishna.models.Model

class Cache(context: Context) {

    private val PREF_NAME: String = "app_pref"
    private val KEY: String = "key"

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    var cachedData: List<Model.Delivery>
        get() = Model.fromJsonString(preferences.getString(KEY, ""))
        set(value) = preferences.edit().putString(KEY, Model.toJsonString(value)).apply()
}
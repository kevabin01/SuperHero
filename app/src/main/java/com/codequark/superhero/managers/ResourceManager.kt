package com.codequark.superhero.managers

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceManager private constructor() {
    lateinit var application: Application
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var resources: Resources

    companion object {
        @Volatile
        private var instance: ResourceManager? = null

        fun getInstance(): ResourceManager = instance ?: synchronized(this) {
            instance ?: ResourceManager().also {
                instance = it
            }
        }
    }

    init {
        if(instance != null) {
            throw RuntimeException("Use getInstance() method to get instance the single instance of this class.")
        }
    }

    fun initialize(@NonNull application: Application) {
        this.application = application
        this.resources = application.applicationContext.resources
        this.preferences = application.getSharedPreferences("SuperHero", Context.MODE_PRIVATE)
    }

    @NonNull
    fun getStringResource(@StringRes id: Int): String {
        return getContext().resources.getString(id)
    }

    @ColorInt
    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(getContext(), id)
    }

    @NonNull
    fun getContext(): Context {
        return application.applicationContext
    }

    fun cleanKey(@StringRes resourceKey: Int) {
        this.editor = preferences.edit()
        this.editor.putString(resources.getString(resourceKey), "")
        this.editor.apply()
    }

    operator fun set(@StringRes resourceKey: Int, value: String) {
        this.editor = preferences.edit()
        this.editor.putString(resources.getString(resourceKey), value)
        this.editor.apply()
    }

    fun getString(resourceKey: Int): String {
        return preferences.getString(resources.getString(resourceKey), "")
            ?: return ""
    }

    operator fun set(resourceKey: Int, value: Int) {
        this.editor = preferences.edit()
        this.editor.putInt(resources.getString(resourceKey), value)
        this.editor.apply()
    }

    fun getInteger(resourceKey: Int): Int {
        return preferences.getInt(resources.getString(resourceKey), 0)
    }

    operator fun set(@StringRes resourceKey: Int, value: Boolean) {
        this.editor = preferences.edit()
        this.editor.putBoolean(resources.getString(resourceKey), value)
        this.editor.apply()
    }

    fun getBoolean(key: Int): Boolean {
        return preferences.getBoolean(resources.getString(key), false)
    }
}
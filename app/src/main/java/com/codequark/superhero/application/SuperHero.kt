package com.codequark.superhero.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.codequark.superhero.managers.ResourceManager
import com.codequark.superhero.managers.WriteManager

open class SuperHero: Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        ResourceManager.getInstance().initialize(this)
        WriteManager.initProject(this, "CodeQuark", "SuperHero")
    }
}
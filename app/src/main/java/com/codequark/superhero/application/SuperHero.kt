package com.codequark.superhero.application

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.codequark.superhero.managers.ImageManager
import com.codequark.superhero.managers.PreferenceManager
import com.codequark.superhero.managers.ResourceManager
import com.codequark.superhero.managers.WriteManager

open class SuperHero: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        ResourceManager.getInstance().initialize(this)
        PreferenceManager.getInstance().initialize(this, "SuperHero")
        ImageManager.instance.initialize(this)
        WriteManager.initProject(this, "CodeQuark", "SuperHero")
    }
}
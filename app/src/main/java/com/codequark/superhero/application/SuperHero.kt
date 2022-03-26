package com.codequark.superhero.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.codequark.superhero.managers.ImageManager
import com.codequark.superhero.managers.PreferenceManager
import com.codequark.superhero.managers.ResourceManager
import com.codequark.superhero.managers.WriteManager

open class SuperHero: Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        ResourceManager.getInstance().initialize(this)
        PreferenceManager.getInstance().initialize(this, "SuperHero")
        ImageManager.instance.initialize(this, true)
        WriteManager.initProject(this, "CodeQuark", "SuperHero")
    }
}
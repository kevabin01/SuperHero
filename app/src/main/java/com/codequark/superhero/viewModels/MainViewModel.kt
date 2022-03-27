package com.codequark.superhero.viewModels

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration
import com.codequark.superhero.models.HeroItem
import com.codequark.superhero.room.models.Hero

class MainViewModel(application: Application): LoginViewModel(application) {
    @NonNull
    private val destination: LiveData<Int> = repository.getDestination()

    @NonNull
    private val handler = Handler(Looper.getMainLooper())

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    @NonNull
    fun getQuery(): String {
        return repository.getQuery()
    }

    val navConfiguration: AppBarConfiguration = repository.navConfiguration

    fun setDestination(@IdRes destination: Int) {
        repository.setDestination(destination)
    }

    fun setQuery(@NonNull query: String) {
        this.repository.setQuery(query)

        if(query.isEmpty()) {
            return
        }

        this.handler.removeCallbacksAndMessages(null)
        this.handler.postDelayed({
            requestSearch(query)
        }, 1000)
    }

    fun getInfo(@NonNull hero: Hero): List<HeroItem> {
        return repository.getInfo(hero)
    }
}
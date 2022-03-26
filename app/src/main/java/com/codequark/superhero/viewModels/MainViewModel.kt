package com.codequark.superhero.viewModels

import android.app.Application
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration

class MainViewModel(application: Application): LoginViewModel(application) {
    @NonNull
    private val destination: LiveData<Int> = repository.getDestination()

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    val navConfiguration: AppBarConfiguration = repository.navConfiguration

    fun setDestination(@IdRes destination: Int) {
        repository.setDestination(destination)
    }

    fun setQuery(@NonNull query: String) {
        this.repository.setQuery(query)
    }
}
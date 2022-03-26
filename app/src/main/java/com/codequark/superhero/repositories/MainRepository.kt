package com.codequark.superhero.repositories

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration
import com.codequark.superhero.R
import com.codequark.superhero.viewModels.SecureLiveData

class MainRepository private constructor(@NonNull context: Context): LoginRepository(context) {
    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(@NonNull context: Context): MainRepository = instance ?: synchronized(this) {
            instance ?: MainRepository(context).also {
                instance = it
            }
        }
    }

    @NonNull
    private val destination = SecureLiveData(0)

    @NonNull
    val navConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
        R.id.navigationLogin,
        R.id.navigationRegister,
        R.id.navigationHome,
        R.id.navigationSearch
    ).build()

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    fun setDestination(@IdRes destination: Int) {
        this.destination.value = destination
        this.destination.postValue(0)
    }

    fun setQuery(@NonNull query: String) {
        
    }

    fun logout() {
        logoutFirebase()
        setDestination(R.id.navigationLogin)
    }
}
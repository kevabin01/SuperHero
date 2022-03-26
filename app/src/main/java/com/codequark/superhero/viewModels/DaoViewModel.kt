package com.codequark.superhero.viewModels

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import com.codequark.superhero.repositories.MainRepository

open class DaoViewModel(application: Application): AndroidViewModel(application) {
    @NonNull
    protected val repository = MainRepository.getInstance(application.applicationContext)
}
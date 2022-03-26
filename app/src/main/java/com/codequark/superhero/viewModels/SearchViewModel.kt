package com.codequark.superhero.viewModels

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.codequark.superhero.repositories.SearchRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    @NonNull
    private val repository: SearchRepository = SearchRepository.getInstance(application.applicationContext)

    @NonNull
    val updating: LiveData<Boolean> = repository.getUpdating()

    @NonNull
    val exception: LiveData<Exception> = repository.getException()

    @NonNull
    val connection: LiveData<Boolean> = repository.getConnection()
}
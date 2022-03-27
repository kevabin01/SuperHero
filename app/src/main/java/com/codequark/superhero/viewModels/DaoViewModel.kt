package com.codequark.superhero.viewModels

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codequark.superhero.repositories.MainRepository
import com.codequark.superhero.room.models.Hero
import kotlinx.coroutines.flow.Flow

open class DaoViewModel(application: Application): AndroidViewModel(application) {
    @NonNull
    protected val repository = MainRepository.getInstance(application.applicationContext)

    @NonNull
    val heroes: Flow<PagingData<Hero>> = repository.getHeroes().cachedIn(viewModelScope)
}
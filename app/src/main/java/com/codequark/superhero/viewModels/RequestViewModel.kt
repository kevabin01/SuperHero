package com.codequark.superhero.viewModels

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.codequark.superhero.models.SuperHero
import com.codequark.superhero.utils.Constants

open class RequestViewModel(application: Application): DaoViewModel(application) {
    @NonNull
    private val updating = repository.getUpdating()

    @NonNull
    private val exception = repository.getException()

    @NonNull
    private val connection = repository.getConnection()

    @NonNull
    val superHero: LiveData<SuperHero> = repository.getSuperHero()

    @NonNull
    val superHeroes: LiveData<List<SuperHero>> = repository.getSuperHeroes()

    @NonNull
    val superHeroId: LiveData<String> = repository.getSuperHeroId()

    @NonNull
    fun getUpdating(): LiveData<Boolean> {
        return updating
    }

    @NonNull
    fun getException(): LiveData<Exception> {
        return exception
    }

    @NonNull
    fun getConnection(): LiveData<Boolean> {
        return connection
    }

    fun setUpdating(@NonNull updating: Boolean) {
        this.repository.setUpdating(updating)
    }

    fun setException(@NonNull ex: Exception) {
        this.repository.setException(ex)
    }

    fun setConnection(@NonNull connection: Boolean) {
        this.repository.setConnection(connection)
    }

    fun setSuperHeroId(@NonNull superHeroId: String) {
        this.repository.setSuperHeroId(superHeroId)
    }

    fun requestSearch(@NonNull query: String) {
        val params: HashMap<String, Any> = HashMap()

        params[Constants.JsonConstants.query] = query

        this.repository.requestSearch(params)
    }

    fun saveSuperHero(superHero: SuperHero) {
        val hero = repository.fromSuperHeroToHero(superHero)
        this.repository.replace(hero)
    }
}
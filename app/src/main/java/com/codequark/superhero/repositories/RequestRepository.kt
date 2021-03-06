package com.codequark.superhero.repositories

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.codequark.superhero.interfaces.ErrorListener
import com.codequark.superhero.interfaces.NetworkListener
import com.codequark.superhero.interfaces.SuccessListener
import com.codequark.superhero.models.SuperHero
import com.codequark.superhero.retrofit.models.Result
import com.codequark.superhero.retrofit.models.SearchResult
import com.codequark.superhero.retrofit.requests.SearchRequest
import com.codequark.superhero.room.models.Hero
import com.codequark.superhero.utils.LogUtils
import com.codequark.superhero.viewModels.SecureLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class RequestRepository(@NonNull context: Context): DaoRepository(context) {
    @NonNull
    private val hero: SecureLiveData<Hero> = SecureLiveData(null)

    @NonNull
    private val superHeroes: SecureLiveData<List<SuperHero>> = SecureLiveData(null)

    @NonNull
    fun getHero(): LiveData<Hero> = hero

    @NonNull
    fun getSuperHeroes(): LiveData<List<SuperHero>> = superHeroes

    fun setHero(@NonNull hero: Hero) {
        this.hero.value = hero
    }

    private fun setSuperHeroes(@NonNull superHeroes: List<SuperHero>) {
        this.superHeroes.value = superHeroes
        this.superHeroes.postValue(null)
    }

    @NonNull
    private val errorListener = object: ErrorListener {
        override fun onError(ex: Exception) {
            setUpdating(false)
            setException(ex)
        }
    }

    @NonNull
    private val networkListener: NetworkListener = object: NetworkListener {
        override fun onConnected() {
            setUpdating(true)
        }

        override fun onDisconnected() {
            setUpdating(false)
            setConnection(true)
        }
    }

    fun requestSearch(@NonNull params: HashMap<String, Any>) {
        val request = SearchRequest()

        request.setSuccessListener(object: SuccessListener<Result> {
            override fun onSuccess(result: Result) {
                setUpdating(false)

                when (result) {
                    is SearchResult -> {
                        if(result.response == "success") {
                            LogUtils.print("Success Search with response: " + result.response + " and resultsFor: " + result.resultsFor)

                            val list: List<SuperHero> = result.results
                            // val heroes = fromSuperHeroesToHeroes(list)
                            // replace(heroes)

                            setSuperHeroes(list)
                        } else {
                            setSuperHeroes(emptyList())
                        }
                    }

                    else -> {
                        throw RuntimeException("Error, unknown model list response (-2)")
                    }
                }
            }
        })

        request.setErrorListener(errorListener)

        request.setNetworkListener(networkListener)

        @Suppress("EXPERIMENTAL_API_USAGE")
        GlobalScope.launch(Dispatchers.IO) {
            request.execute(params)
        }
    }

    fun fromSuperHeroesToHeroes(superHeroes: List<SuperHero>): List<Hero> {
        val heroes = ArrayList<Hero>()

        superHeroes.forEach { superHero ->
            heroes.add(
                Hero(
                    superHero.id,
                    superHero.name,
                    superHero.powerstats.intelligence,
                    superHero.powerstats.strength,
                    superHero.powerstats.speed,
                    superHero.powerstats.durability,
                    superHero.powerstats.power,
                    superHero.powerstats.combat,
                    superHero.biography.fullName,
                    superHero.biography.alterEgos,
                    superHero.biography.placeOfBirth,
                    superHero.biography.firstAppearance,
                    superHero.biography.publisher,
                    superHero.biography.alignment,
                    superHero.appearance.gender,
                    superHero.appearance.race,
                    superHero.appearance.height[1],
                    superHero.appearance.weight[1],
                    superHero.appearance.eyeColor,
                    superHero.appearance.hairColor,
                    superHero.work.occupation,
                    superHero.work.base,
                    superHero.connections.groupAffiliation,
                    superHero.connections.relatives,
                    superHero.image.url
                )
            )
        }

        return heroes
    }

    fun fromSuperHeroToHero(superHero: SuperHero): Hero {
        return Hero(
            superHero.id,
            superHero.name,
            superHero.powerstats.intelligence,
            superHero.powerstats.strength,
            superHero.powerstats.speed,
            superHero.powerstats.durability,
            superHero.powerstats.power,
            superHero.powerstats.combat,
            superHero.biography.fullName,
            superHero.biography.alterEgos,
            superHero.biography.placeOfBirth,
            superHero.biography.firstAppearance,
            superHero.biography.publisher,
            superHero.biography.alignment,
            superHero.appearance.gender,
            superHero.appearance.race,
            superHero.appearance.height[1],
            superHero.appearance.weight[1],
            superHero.appearance.eyeColor,
            superHero.appearance.hairColor,
            superHero.work.occupation,
            superHero.work.base,
            superHero.connections.groupAffiliation,
            superHero.connections.relatives,
            superHero.image.url
        )
    }
}
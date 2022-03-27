package com.codequark.superhero.repositories

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration
import com.codequark.superhero.R
import com.codequark.superhero.models.HeroItem
import com.codequark.superhero.room.models.Hero
import com.codequark.superhero.utils.Constants.ItemDef
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
        R.id.navigationSearch,
        R.id.navigationHistory
    ).build()

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    fun setDestination(@IdRes destination: Int) {
        this.destination.value = destination
        this.destination.postValue(0)
    }

    fun logout() {
        logoutFirebase()
        deleteAll()
        setDestination(R.id.navigationLogin)
    }

    fun getInfo(@NonNull model: Hero): List<HeroItem> {
        val list: MutableList<HeroItem> = ArrayList()

        list.add(HeroItem(ItemDef.HEADER, "SuperHero"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "ID:", model.id))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Name:", model.name))

        list.add(HeroItem(ItemDef.HEADER, "Powerstats"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Intelligence:", model.intelligence))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Strength:", model.strength))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Speed:", model.speed))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Durability:", model.durability))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Power:", model.power))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Combat:", model.combat))

        list.add(HeroItem(ItemDef.HEADER, "Biography"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "FullName:", model.fullName))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Alter Egos:", model.alterEgos))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Place of Birth:", model.placeOfBirth))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "First\nAppearance:", model.firstAppearance))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Publisher:", model.publisher))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Alignment:", model.alignment))

        list.add(HeroItem(ItemDef.HEADER, "Appearance"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Gender:", model.gender))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Race:", model.race))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Height:", model.height))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Weight:", model.weight))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Eye Color:", model.eyeColor))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Hair Color:", model.hairColor))

        list.add(HeroItem(ItemDef.HEADER, "Work"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Occupation:", model.occupation))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Base:", model.base))

        list.add(HeroItem(ItemDef.HEADER, "Connections"))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Group\nAffiliation:", model.groupAffiliation))
        list.add(HeroItem(ItemDef.DIVIDER))
        list.add(HeroItem(ItemDef.CONTENT, R.drawable.ic_next, "Relatives:", model.relatives))

        return list
    }
}
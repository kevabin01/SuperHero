package com.codequark.superhero.repositories

import android.content.Context
import androidx.annotation.NonNull
import androidx.paging.Pager
import androidx.paging.PagingData
import com.codequark.superhero.executors.ioThread
import com.codequark.superhero.room.AppDatabase
import com.codequark.superhero.room.dao.HeroDao
import com.codequark.superhero.room.models.Hero
import kotlinx.coroutines.flow.Flow

open class DaoRepository(@NonNull context: Context): BaseRepository() {
    @NonNull
    private val database = AppDatabase.getInstance(context)

    @NonNull
    private val heroDao: HeroDao = database.heroDao()

    @NonNull
    fun getHeroes(): Flow<PagingData<Hero>> {
        return Pager(pagingConfig) {
            heroDao.getHeroes()
        }.flow
    }

    fun insert(@NonNull model: Hero) = ioThread {
        this.heroDao.insert(model)
    }

    fun insert(@NonNull list: List<Hero>) = ioThread {
        this.heroDao.insert(list)
    }

    fun replace(@NonNull model: Hero) = ioThread {
        this.heroDao.replace(model)
    }

    fun replace(@NonNull list: List<Hero>) = ioThread {
        this.heroDao.replace(list)
    }

    fun update(@NonNull model: Hero) = ioThread {
        this.heroDao.update(model)
    }

    fun update(@NonNull list: List<Hero>) = ioThread {
        this.heroDao.update(list)
    }

    fun delete(@NonNull model: Hero) = ioThread {
        this.heroDao.delete(model)
    }

    fun delete(@NonNull list: List<Hero>) = ioThread {
        this.heroDao.delete(list)
    }

    fun deleteAll() = ioThread {
        this.heroDao.deleteAll()
    }
}
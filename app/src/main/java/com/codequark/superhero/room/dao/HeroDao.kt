package com.codequark.superhero.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.codequark.superhero.room.models.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hero: Hero)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(heroes: List<Hero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(hero: Hero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(heroes: List<Hero>)

    @Update
    fun update(hero: Hero)

    @Update
    fun update(heroes: List<Hero>)

    @Delete
    fun delete(hero: Hero)

    @Delete
    fun delete(heroes: List<Hero>)

    @Query("DELETE FROM tableHeroes")
    fun deleteAll()

    @Query("SELECT * FROM tableHeroes ORDER BY id")
    fun getHeroes(): PagingSource<Int, Hero>
}
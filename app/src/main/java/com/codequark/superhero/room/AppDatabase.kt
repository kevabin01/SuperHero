package com.codequark.superhero.room

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codequark.superhero.room.dao.HeroDao
import com.codequark.superhero.room.models.Hero

@Database(
    entities = [Hero::class],
    version = RoomConstants.databaseVersion
)
abstract class AppDatabase: RoomDatabase() {
    @NonNull
    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(@NonNull context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val it = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    RoomConstants.databaseName
                ).build()

                instance = it
                it
            }
        }
    }
}
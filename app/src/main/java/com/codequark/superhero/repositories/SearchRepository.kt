package com.codequark.superhero.repositories

import android.content.Context
import androidx.annotation.NonNull

class SearchRepository private constructor(@NonNull context: Context): BaseRepository() {
    companion object {
        @Volatile
        private var instance: SearchRepository? = null

        fun getInstance(@NonNull context: Context): SearchRepository = instance ?: synchronized(this) {
            instance ?: SearchRepository(context).also {
                instance = it
            }
        }
    }
}
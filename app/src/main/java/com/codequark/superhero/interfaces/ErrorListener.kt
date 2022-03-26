package com.codequark.superhero.interfaces

import androidx.annotation.NonNull

interface ErrorListener {
    fun onError(@NonNull ex: Exception)
}
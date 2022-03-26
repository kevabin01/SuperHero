package com.codequark.superhero.interfaces

import androidx.annotation.NonNull

interface SuccessListener<T> {
    fun onSuccess(@NonNull result: T)
}
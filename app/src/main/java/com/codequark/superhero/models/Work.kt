package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Work(
    @NonNull
    @SerializedName("occupation")
    val occupation: String,

    @NonNull
    @SerializedName("base")
    val base: String
)
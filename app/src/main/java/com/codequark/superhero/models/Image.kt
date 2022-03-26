package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Image(
    @NonNull
    @SerializedName("url")
    val url: String
)
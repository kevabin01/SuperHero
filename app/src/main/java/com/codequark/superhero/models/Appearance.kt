package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Appearance(
    @NonNull
    @SerializedName("gender")
    val gender: String,

    @NonNull
    @SerializedName("race")
    val race: String,

    @NonNull
    @SerializedName("height")
    val height: List<String>,

    @NonNull
    @SerializedName("weight")
    val weight: List<String>,

    @NonNull
    @SerializedName("eye-color")
    val eyeColor: String,

    @NonNull
    @SerializedName("hair-color")
    val hairColor: String
)
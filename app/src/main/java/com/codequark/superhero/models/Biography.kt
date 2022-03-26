package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Biography(
    @NonNull
    @SerializedName("full-name")
    val fullName: String,

    @NonNull
    @SerializedName("alter-egos")
    val alterEgos: String,

    @NonNull
    @SerializedName("aliases")
    val aliases: List<String>,

    @NonNull
    @SerializedName("place-of-birth")
    val placeOfBirth: String,

    @NonNull
    @SerializedName("first-appearance")
    val firstAppearance: String,

    @NonNull
    @SerializedName("publisher")
    val publisher: String,

    @NonNull
    @SerializedName("alignment")
    val alignment: String
)
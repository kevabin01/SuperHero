package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class PowerStats(
    @NonNull
    @SerializedName("intelligence")
    val intelligence: String,

    @NonNull
    @SerializedName("strength")
    val strength: String,

    @NonNull
    @SerializedName("speed")
    val speed: String,

    @NonNull
    @SerializedName("durability")
    val durability: String,

    @NonNull
    @SerializedName("power")
    val power: String,

    @NonNull
    @SerializedName("combat")
    val combat: String
)
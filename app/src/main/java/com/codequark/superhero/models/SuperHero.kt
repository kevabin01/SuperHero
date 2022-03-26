package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.codequark.superhero.retrofit.models.Result
import com.google.gson.annotations.SerializedName

data class SuperHero(
    @NonNull
    @SerializedName("id")
    val id: String,

    @NonNull
    @SerializedName("name")
    val name: String,

    @NonNull
    @SerializedName("powerstats")
    val powerstats: PowerStats,

    @NonNull
    @SerializedName("biography")
    val biography: Biography,

    @NonNull
    @SerializedName("appearance")
    val appearance: Appearance,

    @NonNull
    @SerializedName("work")
    val work: Work,

    @NonNull
    @SerializedName("connections")
    val connections: Connections,

    @NonNull
    @SerializedName("image")
    val image: Image
): Result()
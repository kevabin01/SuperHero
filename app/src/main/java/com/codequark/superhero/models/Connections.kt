package com.codequark.superhero.models

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class Connections(
    @NonNull
    @SerializedName("group-affiliation")
    val groupAffiliation: String,

    @NonNull
    @SerializedName("relatives")
    val relatives: String
)
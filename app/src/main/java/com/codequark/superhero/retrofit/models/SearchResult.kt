package com.codequark.superhero.retrofit.models

import androidx.annotation.NonNull
import com.codequark.superhero.models.SuperHero
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @NonNull
    @SerializedName("response")
    val response: String,

    @NonNull
    @SerializedName("results-for")
    val resultsFor: String,

    @NonNull
    @SerializedName("results")
    val results: List<SuperHero>
): Result()
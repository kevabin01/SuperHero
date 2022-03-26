package com.codequark.superhero.retrofit.services

import androidx.annotation.NonNull
import com.codequark.superhero.retrofit.models.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("/api/{accessToken}/search/{query}")
    suspend fun request(
        @NonNull
        @Path("accessToken")
        accessToken: String,

        @NonNull
        @Path("query")
        query: String
    ): Response<SearchResult>
}
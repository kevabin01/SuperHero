package com.codequark.superhero.retrofit.requests

import androidx.annotation.NonNull
import com.codequark.superhero.retrofit.Endpoints
import com.codequark.superhero.retrofit.base.RetrofitRequest
import com.codequark.superhero.retrofit.models.Result
import com.codequark.superhero.retrofit.services.SearchService
import com.codequark.superhero.utils.Constants.JsonConstants
import java.io.IOException

class SearchRequest: RetrofitRequest(
    "Search",
    Endpoints.baseUrl
) {
    override suspend fun onExecute(@NonNull params: HashMap<String, Any>): Result? {
        try {
            val apiKey = Endpoints.API_KEY
            val query: String = params[JsonConstants.query] as String

            val service: SearchService = retrofit.create(SearchService::class.java)
            @Suppress("BlockingMethodInNonBlockingContext")
            val response = service.request(apiKey, query)

            if(response.isSuccessful) {
                val model = response.body()

                if(model != null) {
                    try {
                        return model
                    } catch (ex: Exception) {
                        onError(ex)
                    }
                }
            } else {
                onError(RuntimeException("Error on Execution"))
            }
        } catch (ex: IOException) {
            onError(ex)
        }

        return null
    }
}
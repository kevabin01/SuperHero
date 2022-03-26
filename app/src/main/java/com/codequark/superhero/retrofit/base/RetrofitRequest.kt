package com.codequark.superhero.retrofit.base

import androidx.annotation.NonNull
import com.codequark.superhero.interfaces.ErrorListener
import com.codequark.superhero.interfaces.NetworkListener
import com.codequark.superhero.interfaces.SuccessListener
import com.codequark.superhero.retrofit.managers.RequestManager
import com.codequark.superhero.retrofit.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class RetrofitRequest(
    @NonNull
    TAG: String,

    @NonNull
    baseUrl: String
): RequestManager<Result>(TAG, baseUrl, 15L) {
    private var successListener: SuccessListener<Result>? = null

    private var errorListener: ErrorListener? = null

    private var networkListener: NetworkListener? = null

    open fun setSuccessListener(@NonNull successListener: SuccessListener<Result>) {
        this.successListener = successListener
    }

    open fun setErrorListener(@NonNull errorListener: ErrorListener) {
        this.errorListener = errorListener
    }

    open fun setNetworkListener(@NonNull networkListener: NetworkListener) {
        this.networkListener = networkListener
    }

    suspend fun onSuccess(@NonNull result: Result) {
        withContext(Dispatchers.Main) {
            successListener?.onSuccess(result)
        }
    }

    suspend fun onError(@NonNull ex: Exception) {
        withContext(Dispatchers.Main) {
            errorListener?.onError(ex)
        }
    }

    suspend fun onConnected() {
        withContext(Dispatchers.Main) {
            networkListener?.onConnected()
        }
    }

    suspend fun onDisconnected() {
        withContext(Dispatchers.Main) {
            networkListener?.onDisconnected()
        }
    }

    suspend fun sendError(@NonNull response: Response<String>) {
        val content: String = if(response.body() != null) {
            "Code: " + response.code() + " - Response: " + response.body()
        } else {
            "Code: " + response.code()
        }

        onError(RuntimeException(content))
    }
}
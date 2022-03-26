package com.codequark.superhero.models.login

import androidx.annotation.NonNull

data class Login(
    @NonNull
    val firebaseId: String,

    @NonNull
    val user: String,

    @NonNull
    val password: String
) {
    constructor(): this("", "", "")

    constructor(
        @NonNull
        user: String,

        @NonNull
        password: String
    ): this("", user, password)

    @NonNull
    fun getEmail(): String {
        return "$user@test.com"
    }
}
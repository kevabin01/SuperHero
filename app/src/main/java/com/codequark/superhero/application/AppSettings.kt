package com.codequark.superhero.application

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.codequark.superhero.R
import com.codequark.superhero.managers.PreferenceManager
import com.codequark.superhero.managers.ResourceManager
import com.codequark.superhero.models.login.Login
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppSettings {
    companion object {
        fun login(@NonNull login: Login) {
            setLogin(login)
        }

        fun logout() {
            PreferenceManager.getInstance().cleanKey(R.string.key_login)
        }

        private fun setLogin(@NonNull login: Login) {
            val gson = Gson()
            val json = gson.toJson(login)
            PreferenceManager.getInstance().set(R.string.key_login, json)
        }

        @Nullable
        fun getLogin(): Login? {
            return try {
                val gson = Gson()
                val type = object: TypeToken<Login>(){}.type
                val json = ResourceManager.getInstance().getString(R.string.key_login)

                gson.fromJson(json, type)
            } catch (ex: Exception) {
                null
            }
        }
    }
}
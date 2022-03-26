package com.codequark.superhero.application

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.codequark.superhero.R
import com.codequark.superhero.managers.PreferenceManager
import com.codequark.superhero.models.login.Login
import com.codequark.superhero.utils.LogUtils

class AppSettings {
    companion object {
        fun login(@NonNull login: Login) {
            setLogin(login)
        }

        fun logout() {
            PreferenceManager.getInstance().cleanKey(R.string.key_firebase_id)
            PreferenceManager.getInstance().cleanKey(R.string.key_email)
            PreferenceManager.getInstance().cleanKey(R.string.key_password)
        }

        private fun setLogin(@NonNull login: Login) {
            PreferenceManager.getInstance().set(R.string.key_firebase_id, login.firebaseId)
            PreferenceManager.getInstance().set(R.string.key_email, login.email)
            PreferenceManager.getInstance().set(R.string.key_password, login.password)
        }

        @Nullable
        fun getLogin(): Login? {
            val firebaseId = PreferenceManager.getInstance().getString(R.string.key_firebase_id)
            val email = PreferenceManager.getInstance().getString(R.string.key_email)
            val password = PreferenceManager.getInstance().getString(R.string.key_password)

            LogUtils.print("firebaseId: $firebaseId")
            LogUtils.print("email: $email")
            LogUtils.print("password: $password")

            return if(firebaseId.isEmpty() || email.isEmpty() || password.isEmpty()) {
                null
            } else {
                Login(firebaseId, email, password)
            }
        }
    }
}
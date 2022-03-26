package com.codequark.superhero.viewModels

import android.app.Application
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration
import com.codequark.superhero.R
import com.codequark.superhero.managers.NetworkManager.LoginStateDef
import com.codequark.superhero.models.login.Login
import com.codequark.superhero.repositories.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    @NonNull
    private val repository: MainRepository = MainRepository.getInstance(application.applicationContext)

    @NonNull
    private val updating: LiveData<Boolean> = repository.getUpdating()

    @NonNull
    private val loginState: LiveData<Int> = repository.getLoginState()

    @NonNull
    private val destination: LiveData<Int> = repository.getDestination()

    var email = ""
    var password = ""

    @NonNull
    fun getUpdating(): LiveData<Boolean> {
        return updating
    }

    @NonNull
    fun getLoginState(): LiveData<Int> {
        return loginState
    }

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    val navConfiguration: AppBarConfiguration = repository.navConfiguration

    fun setUpdating(updating: Boolean) {
        repository.setUpdating(updating)
    }

    fun setLoginState(@LoginStateDef state: Int) {
        repository.setLoginState(state)
    }

    fun setDestination(@IdRes destination: Int) {
        repository.setDestination(destination)
    }

    fun setQuery(@NonNull query: String) {
        this.repository.setQuery(query)
    }

    fun login(@NonNull view: View) {
        email = email.trim()

        if(email.isEmpty()) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_USUARIO_EMPTY)
            return
        }

        if(password.isEmpty()) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY)
            return
        }

        if(password.length < 6) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LONGITUD)
            return
        }

        val login = Login(email, password)
        repository.login(login)
    }

    fun logout() {
        repository.logout()
    }

    fun registrarse(@NonNull view: View) {
        setDestination(R.id.navigationRegister)
    }

    fun registrar(@NonNull view: View) {
        email = email.trim()

        if(email.isEmpty()) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_USUARIO_EMPTY)
            return
        }

        if(password.isEmpty()) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY)
            return
        }

        if(password.length < 6) {
            setLoginState(LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LONGITUD)
            return
        }

        val login = Login(email, password)
        repository.registrar(login)
    }
}
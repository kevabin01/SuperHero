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
    private val loginState: LiveData<Int> = repository.getLoginState()

    @NonNull
    private val updating: LiveData<Boolean> = repository.getUpdating()

    @NonNull
    private val destination: LiveData<Int> = repository.getDestination()

    var usuario = ""
    var password = ""

    @NonNull
    fun getLoginState(): LiveData<Int> {
        return loginState
    }

    @NonNull
    fun getUpdating(): LiveData<Boolean> {
        return updating
    }

    @NonNull
    fun getDestination(): LiveData<Int> {
        return destination
    }

    val navConfiguration: AppBarConfiguration = repository.navConfiguration

    fun setLoginState(@LoginStateDef state: Int) {
        repository.setLoginState(state)
    }

    fun setUpdating(updating: Boolean) {
        repository.setUpdating(updating)
    }

    fun setDestination(@IdRes destination: Int) {
        repository.setDestination(destination)
    }

    fun login(@NonNull view: View) {
        if(usuario.isEmpty()) {
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

        val login = Login(usuario, password)
        repository.login(login)
    }

    fun logout() {
        repository.logout()
    }

    fun registrarse(@NonNull view: View) {
        setDestination(R.id.navigationRegister)
    }

    fun registrar(@NonNull view: View) {
        if(usuario.isEmpty()) {
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

        val login = Login(usuario, password)
        repository.registrar(login)
    }
}
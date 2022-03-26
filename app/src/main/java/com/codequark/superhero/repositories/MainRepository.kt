package com.codequark.superhero.repositories

import android.content.Context
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.navigation.ui.AppBarConfiguration
import com.codequark.superhero.R
import com.codequark.superhero.application.AppSettings
import com.codequark.superhero.managers.NetworkManager.LoginStateDef
import com.codequark.superhero.models.login.Login
import com.codequark.superhero.viewModels.SecureLiveData
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class MainRepository private constructor(@NonNull context: Context) {
    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(@NonNull context: Context): MainRepository = instance ?: synchronized(this) {
            instance ?: MainRepository(context).also {
                instance = it
            }
        }
    }

    @NonNull
    private val firebaseAuth = FirebaseAuth.getInstance()

    @NonNull
    private val loginState = SecureLiveData(LoginStateDef.STATE_DEFAULT)

    @NonNull
    private val updating = SecureLiveData(false)

    @NonNull
    private val destination = SecureLiveData(0)

    @NonNull
    val navConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
        R.id.navigationLogin,
        R.id.navigationRegister,
        R.id.navigationHome,
        R.id.navigationSearch
    ).build()

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

    fun setUpdating(@NonNull updating: Boolean) {
        this.updating.value = updating
    }

    fun setLoginState(@LoginStateDef state: Int) {
        this.loginState.value = state
        this.loginState.postValue(LoginStateDef.STATE_DEFAULT)
    }

    fun setDestination(@IdRes destination: Int) {
        this.destination.value = destination
        this.destination.postValue(0)
    }

    fun setQuery(@NonNull query: String) {

    }

    fun login(login: Login) {
        setUpdating(true)

        val task = firebaseAuth.signInWithEmailAndPassword(login.email, login.password)

        task.addOnSuccessListener(OnSuccessListener { authResult ->
            if(authResult.user == null) {
                return@OnSuccessListener
            }
            val firebaseId = authResult.user!!.uid
            AppSettings.login(Login(firebaseId, login.email, login.password))
            setLoginState(LoginStateDef.STATE_LOGIN_SUCCESS)
        })
        task.addOnFailureListener { ex ->
            when (ex) {
                is FirebaseNetworkException -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_NETWORK)
                }

                is FirebaseAuthInvalidCredentialsException -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_PASSWORD)
                }

                is FirebaseAuthInvalidUserException -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_NOT_EXISTS)
                }

                is FirebaseTooManyRequestsException -> {
                    // We have blocked all requests from this device due to unusual activity. Try again later. [ Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later. ]
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_MANY_REQUESTS)
                }

                else -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR)
                }
            }

            Log.d("Kevin", "Login Exception: $ex")
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        AppSettings.logout()
        setDestination(R.id.navigationLogin)
    }

    fun registrar(login: Login) {
        setUpdating(true)
        val task = firebaseAuth.createUserWithEmailAndPassword(login.email, login.password)
        task.addOnSuccessListener {
            val firebaseUser = firebaseAuth.currentUser

            if(firebaseUser != null) {
                AppSettings.login(Login(firebaseUser.uid, login.email, login.password))
                setLoginState(LoginStateDef.STATE_LOGIN_SUCCESS)
            } else {
                setLoginState(LoginStateDef.STATE_LOGIN_ERROR)
            }
        }

        task.addOnFailureListener { ex ->
            when (ex) {
                is FirebaseNetworkException -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_NETWORK)
                }

                is FirebaseAuthUserCollisionException -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR_EXISTS)
                }

                else -> {
                    setLoginState(LoginStateDef.STATE_LOGIN_ERROR)
                }
            }

            Log.d("Kevin", "Registro Exception: $ex")
        }
    }
}
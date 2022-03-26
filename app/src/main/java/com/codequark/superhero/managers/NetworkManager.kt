package com.codequark.superhero.managers

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.IntDef
import androidx.annotation.NonNull

class NetworkManager {
    companion object {
        private var connected: Boolean = false

        fun setConnected(connected: Boolean) {
            this.connected = connected
        }

        fun isNetworkConnected(): Boolean {
            return connected
        }

        @Suppress("deprecation")
        fun isConnected(@NonNull context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false

            return networkInfo.isConnected
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(NetworkStateDef.DEFAULT, NetworkStateDef.CONNECTED, NetworkStateDef.DISCONNECTED)
    annotation class NetworkStateDef {
        companion object {
            const val DEFAULT = 0
            const val CONNECTED = 1
            const val DISCONNECTED = 2
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        LoginStateDef.STATE_DEFAULT,
        LoginStateDef.STATE_LOGIN_SUCCESS,
        LoginStateDef.STATE_LOGIN_ERROR,
        LoginStateDef.STATE_LOGIN_ERROR_NETWORK,
        LoginStateDef.STATE_LOGIN_ERROR_USUARIO,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD,
        LoginStateDef.STATE_LOGIN_ERROR_USUARIO_EMPTY,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LONGITUD,
        LoginStateDef.STATE_LOGIN_ERROR_EXISTS,
        LoginStateDef.STATE_LOGIN_ERROR_NOT_EXISTS,
        LoginStateDef.STATE_LOGIN_ERROR_MANY_REQUESTS
    )
    annotation class LoginStateDef {
        companion object {
            const val STATE_DEFAULT = 0
            const val STATE_LOGIN_SUCCESS = 1
            const val STATE_LOGIN_ERROR = 2
            const val STATE_LOGIN_ERROR_NETWORK = 3
            const val STATE_LOGIN_ERROR_USUARIO = 4
            const val STATE_LOGIN_ERROR_PASSWORD = 5
            const val STATE_LOGIN_ERROR_USUARIO_EMPTY = 6
            const val STATE_LOGIN_ERROR_PASSWORD_EMPTY = 7
            const val STATE_LOGIN_ERROR_PASSWORD_LONGITUD = 8
            const val STATE_LOGIN_ERROR_EXISTS = 9
            const val STATE_LOGIN_ERROR_NOT_EXISTS = 10
            const val STATE_LOGIN_ERROR_MANY_REQUESTS = 11
        }
    }
}
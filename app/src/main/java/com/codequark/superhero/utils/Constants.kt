package com.codequark.superhero.utils

import androidx.annotation.IntDef
import androidx.annotation.StringDef

class Constants {
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
        JsonConstants.query
    )
    annotation class JsonConstants {
        companion object {
            const val query = "query"
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        ItemDef.HEADER,
        ItemDef.DIVIDER,
        ItemDef.CONTENT
    )
    annotation class ItemDef {
        companion object {
            const val HEADER = 1
            const val DIVIDER = 2
            const val CONTENT = 3
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        LoginStateDef.STATE_DEFAULT,
        LoginStateDef.STATE_LOGIN_SUCCESS,
        LoginStateDef.STATE_LOGIN_ERROR,
        LoginStateDef.STATE_LOGIN_ERROR_NETWORK,
        LoginStateDef.STATE_LOGIN_ERROR_EMAIL,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD,
        LoginStateDef.STATE_LOGIN_ERROR_EMAIL_EMPTY,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY,
        LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LENGTH,
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
            const val STATE_LOGIN_ERROR_EMAIL = 4
            const val STATE_LOGIN_ERROR_PASSWORD = 5
            const val STATE_LOGIN_ERROR_EMAIL_EMPTY = 6
            const val STATE_LOGIN_ERROR_PASSWORD_EMPTY = 7
            const val STATE_LOGIN_ERROR_PASSWORD_LENGTH = 8
            const val STATE_LOGIN_ERROR_EXISTS = 9
            const val STATE_LOGIN_ERROR_NOT_EXISTS = 10
            const val STATE_LOGIN_ERROR_MANY_REQUESTS = 11
        }
    }
}
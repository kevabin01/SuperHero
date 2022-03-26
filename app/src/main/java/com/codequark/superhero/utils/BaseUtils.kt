package com.codequark.superhero.utils

import androidx.annotation.NonNull
import com.codequark.superhero.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

class BaseUtils {
    companion object {
        @NonNull
        fun isTest(): Boolean {
            return BuildConfig.BUILD_TYPE == "debug" || BuildConfig.BUILD_TYPE == "qa"
        }

        @NonNull
        fun getTimestamp(): Long {
            return System.currentTimeMillis()
        }

        @NonNull
        fun getDateTimeFormatted(@NonNull timestamp: Long): String {
            val format = SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault())
            return format.format(Date(timestamp))
        }
    }
}
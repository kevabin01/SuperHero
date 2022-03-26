package com.codequark.superhero.utils

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
}
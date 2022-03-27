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
}
package com.codequark.superhero.managers

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.codequark.superhero.R
import java.io.File

@Suppress("unused")
class ImageManager {
    private var isDefaultOptions: Boolean = false

    private val defaultOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .override(Target.SIZE_ORIGINAL)
            .dontTransform()

    private val noneOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .override(Target.SIZE_ORIGINAL)
        .dontTransform()

    private lateinit var thumbRequest: RequestBuilder<Drawable>
    private lateinit var errorRequest: RequestBuilder<Drawable>

    companion object {
        var instance = ImageManager()
    }

    fun initialize(@NonNull application: Application, isDefaultOptions: Boolean) {
        this.isDefaultOptions = isDefaultOptions

        this.errorRequest = Glide.with(application.applicationContext)
            .load(R.drawable.ic_error)
            .apply(getOptions())

        this.thumbRequest = Glide.with(application.applicationContext)
            .load(R.drawable.ic_loading)
            .apply(getOptions())
    }

    fun setImage(@NonNull file: File, @NonNull image: ImageView) {
        Glide.with(image)
            .load(file)
            .apply(getOptions())
            .thumbnail(thumbRequest)
            .error(errorRequest)
            .into(image)
    }

    fun setImage(@NonNull file: File, @NonNull image: ImageView, @NonNull x: Int, @NonNull y: Int) {
        Glide.with(image)
            .load(file)
            .apply(getOptions())
            .thumbnail(thumbRequest)
            .error(errorRequest)
            .override(x, y)
            .into(image)
    }

    fun setImage(@NonNull url: String, @NonNull image: ImageView) {
        Glide.with(image)
            .load(url)
            .apply(getOptions())
            .thumbnail(thumbRequest)
            .error(errorRequest)
            .into(image)
    }

    fun setImage(@Nullable bitmap: Bitmap?, @NonNull image: ImageView) {
        if(bitmap == null) {
            return
        }

        Glide.with(image)
            .load(bitmap)
            .apply(getOptions())
            .thumbnail(thumbRequest)
            .error(errorRequest)
            .into(image)
    }

    fun setImage(@NonNull resource: Int, @NonNull image: ImageView) {
        Glide.with(image)
            .load(resource)
            .apply(getOptions())
            .thumbnail(thumbRequest)
            .error(errorRequest)
            .into(image)
    }

    private fun getOptions(): RequestOptions {
        return if(isDefaultOptions) {
            defaultOptions
        } else {
            noneOptions
        }
    }
}
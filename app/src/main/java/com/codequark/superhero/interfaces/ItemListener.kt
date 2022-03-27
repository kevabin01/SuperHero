package com.codequark.superhero.interfaces

import android.view.View
import androidx.annotation.NonNull

interface ItemListener<Model> {
    fun onItemSelected(@NonNull view: View, @NonNull item: Model) {}

    fun onItemSelected(@NonNull item: Model) {}

    fun onItemSelected(@NonNull item: Model, @NonNull position: Int) {}

    fun onItemSelected(@NonNull item: Model, @NonNull checked: Boolean) {}

    fun onDataSet(@NonNull isEmpty: Boolean, @NonNull itemCount: Int) {}

    fun onRestoreState() {}
}
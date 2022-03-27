package com.codequark.superhero.models.diff

import androidx.recyclerview.widget.DiffUtil
import com.codequark.superhero.room.models.Hero

class DiffUtils {
    companion object {
        val HERO_ITEM_CALLBACK: DiffUtil.ItemCallback<Hero> = object: DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }
}
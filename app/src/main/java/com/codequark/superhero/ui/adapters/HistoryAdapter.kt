package com.codequark.superhero.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.codequark.superhero.base.PagingAdapter
import com.codequark.superhero.databinding.ItemHistoryBinding
import com.codequark.superhero.interfaces.ItemListener
import com.codequark.superhero.managers.ImageManager
import com.codequark.superhero.models.diff.DiffUtils
import com.codequark.superhero.room.models.Hero
import com.codequark.superhero.viewHolders.BindingHolder

class HistoryAdapter(listener: ItemListener<Hero>): PagingAdapter<Hero>(DiffUtils.HERO_ITEM_CALLBACK, listener) {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(@NonNull holder: BindingHolder, @NonNull position: Int) {
        val item = getItem(position) ?: return

        if(holder.binding is ItemHistoryBinding) {
            val binding: ItemHistoryBinding = holder.binding

            ImageManager.instance.setImage(item.imageUrl, binding.ivImage)

            binding.tvName.text = item.name

            binding.container.setOnClickListener {
                listener.onItemSelected(item)
            }

            binding.tvName.setOnClickListener {
                listener.onItemSelected(item)
            }

            binding.ivImage.setOnClickListener {
                listener.onItemSelected(item)
            }
        }
    }
}
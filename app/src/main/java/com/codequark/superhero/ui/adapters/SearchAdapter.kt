package com.codequark.superhero.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.codequark.superhero.base.BaseAdapter
import com.codequark.superhero.databinding.ItemSearchBinding
import com.codequark.superhero.interfaces.ItemListener
import com.codequark.superhero.managers.ImageManager
import com.codequark.superhero.models.SuperHero
import com.codequark.superhero.viewHolders.BindingHolder

class SearchAdapter(listener: ItemListener<SuperHero>): BaseAdapter<SuperHero>(listener) {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(bindingHolder: BindingHolder, position: Int) {
        if(bindingHolder.binding is ItemSearchBinding) {
            val binding = bindingHolder.binding
            val item: SuperHero = list[position]

            binding.tvName.text = item.name

            binding.tvPublisher.text = item.biography.publisher

            ImageManager.instance.setImage(item.image.url, binding.ivImage)

            binding.container.setOnClickListener {
                listener.onItemSelected(item)
            }
        }
    }
}
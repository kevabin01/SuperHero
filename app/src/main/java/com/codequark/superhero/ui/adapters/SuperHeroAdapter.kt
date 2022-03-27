package com.codequark.superhero.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codequark.superhero.base.BaseAdapter
import com.codequark.superhero.databinding.ItemContentBinding
import com.codequark.superhero.databinding.ItemDividerBinding
import com.codequark.superhero.databinding.ItemHeaderBinding
import com.codequark.superhero.models.HeroItem
import com.codequark.superhero.utils.Constants.ItemDef
import com.codequark.superhero.viewHolders.BindingHolder

class SuperHeroAdapter: BaseAdapter<HeroItem>() {
    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        val id = item.id

        if(id == ItemDef.HEADER) {
            return ItemDef.HEADER
        } else if(id == ItemDef.DIVIDER) {
            return ItemDef.DIVIDER
        }

        return ItemDef.CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        return when (viewType) {
            ItemDef.HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BindingHolder(binding)
            }

            ItemDef.DIVIDER -> {
                val binding = ItemDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BindingHolder(binding)
            }

            else -> {
                val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BindingHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(bindingHolder: BindingHolder, position: Int) {
        val item = list[position]

        when(bindingHolder.binding) {
            is ItemHeaderBinding -> {
                val holder = bindingHolder.binding

                holder.tvTitle.text = item.title
            }

            is ItemContentBinding -> {
                val holder = bindingHolder.binding

                holder.tvTitle.text = item.title

                holder.tvText.text = item.text

                holder.ivImage.setImageResource(item.icon)

                holder.container.setOnClickListener {
                    listener.onItemSelected(item)
                }
            }
        }
    }
}
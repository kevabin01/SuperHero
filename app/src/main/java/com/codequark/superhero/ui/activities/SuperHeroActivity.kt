package com.codequark.superhero.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codequark.superhero.databinding.ActivitySuperHeroBinding
import com.codequark.superhero.ui.adapters.SuperHeroAdapter
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.ViewModelFactory

class SuperHeroActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroBinding

    private lateinit var adapter: SuperHeroAdapter

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SuperHeroAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.hero.observe(this) { hero ->
            if(hero == null) {
                return@observe
            }

            adapter.setContent(viewModel.getInfo(hero))
        }
    }
}
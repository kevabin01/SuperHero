package com.codequark.superhero.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codequark.superhero.databinding.FragmentSearchBinding
import com.codequark.superhero.interfaces.ItemListener
import com.codequark.superhero.models.SuperHero
import com.codequark.superhero.ui.activities.SuperHeroActivity
import com.codequark.superhero.ui.adapters.SearchAdapter
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.ViewModelFactory

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SearchAdapter(object: ItemListener<SuperHero> {
            override fun onItemSelected(item: SuperHero) {
                viewModel.setSuperHero(item)
                startActivity(Intent(requireContext(), SuperHeroActivity::class.java))
            }

            override fun onDataSet(isEmpty: Boolean, itemCount: Int) {
                val query = viewModel.getQuery()

                if(query.isEmpty() && isEmpty) {
                    binding.layoutStart.containerStart.visibility = View.VISIBLE
                    binding.layoutEmpty.containerEmpty.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                } else if(isEmpty) {
                    binding.layoutStart.containerStart.visibility = View.GONE
                    binding.layoutEmpty.containerEmpty.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.layoutStart.containerStart.visibility = View.GONE
                    binding.layoutEmpty.containerEmpty.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }

                binding.recyclerView.smoothScrollToPosition(0)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.superHeroes.observe(viewLifecycleOwner) {
            if(it == null) {
                return@observe
            }

            adapter.setContent(it)
        }
    }
}
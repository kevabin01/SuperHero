package com.codequark.superhero.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codequark.superhero.databinding.FragmentHistoryBinding
import com.codequark.superhero.interfaces.ItemListener
import com.codequark.superhero.managers.NetworkManager
import com.codequark.superhero.room.models.Hero
import com.codequark.superhero.ui.adapters.HistoryAdapter
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryFragment: Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HistoryAdapter(object: ItemListener<Hero> {
            override fun onItemSelected(@NonNull item: Hero) {
                if(NetworkManager.isNetworkConnected()) {
                    //viewModel.openGraph(item)
                } else {
                    viewModel.setConnection(true)
                }
            }

            override fun onDataSet(isEmpty: Boolean, itemCount: Int) {
                if(isEmpty) {
                    binding.layoutStart.containerStart.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.layoutStart.containerStart.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.heroes.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}
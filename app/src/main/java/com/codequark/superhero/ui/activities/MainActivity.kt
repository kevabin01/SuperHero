package com.codequark.superhero.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.codequark.superhero.R
import com.codequark.superhero.databinding.ActivityMainBinding
import com.codequark.superhero.managers.NetworkManager.NetworkStateDef
import com.codequark.superhero.retrofit.requests.SearchRequest
import com.codequark.superhero.utils.Constants
import com.codequark.superhero.utils.LogUtils
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.NetworkViewModel
import com.codequark.superhero.viewModels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    private val networkViewModel by viewModels<NetworkViewModel> {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.layoutToolbar.toolbar)
        val actionBar = supportActionBar

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            binding.layoutToolbar.toolbar.navigationIcon = null
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: throw RuntimeException("NavHostFragment is null")

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            manageDestination(destination)
        }

        NavigationUI.setupActionBarWithNavController(this, navController, viewModel.navConfiguration)

        viewModel.getDestination().observe(this) { destination ->
            if(destination == 0) {
                return@observe
            }

            navController.navigate(destination)
        }

        networkViewModel.network.observe(this) { integer ->
            when (integer) {
                NetworkStateDef.DEFAULT -> {
                    LogUtils.print("Default case for initialize NetworkCheck")
                }

                NetworkStateDef.CONNECTED -> {

                }

                NetworkStateDef.DISCONNECTED -> {

                }
            }
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        GlobalScope.launch(Dispatchers.IO) {
            val params: HashMap<String, Any> = HashMap()

            val query = "batman"
            params[Constants.JsonConstants.query] = query

            val request = SearchRequest()
            //request.execute(params)
        }
    }

    private fun manageDestination(@NonNull destination: NavDestination) {
        if(destination.id == R.id.navigationLogin || destination.id == R.id.navigationRegister) {
            binding.fab.visibility = View.GONE
        } else {
            binding.fab.visibility = View.VISIBLE
        }

        viewModel.setQuery("")
    }
}
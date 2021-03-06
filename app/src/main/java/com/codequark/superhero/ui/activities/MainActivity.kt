package com.codequark.superhero.ui.activities

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.codequark.superhero.R
import com.codequark.superhero.databinding.ActivityMainBinding
import com.codequark.superhero.managers.NetworkManager.NetworkStateDef
import com.codequark.superhero.ui.dialogs.LoadingDialog
import com.codequark.superhero.utils.LogUtils
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.NetworkViewModel
import com.codequark.superhero.viewModels.ViewModelFactory

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    private val networkViewModel by viewModels<NetworkViewModel> {
        ViewModelFactory()
    }

    private lateinit var loadingBuilder: LoadingDialog.Builder

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

        binding.layoutToolbar.appBarLayout.outlineProvider = null
        NavigationUI.setupActionBarWithNavController(this, navController, viewModel.navConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        loadingBuilder = LoadingDialog.Builder(this)

        binding.bottomNavigationView.setOnItemReselectedListener {

        }

        viewModel.getDestination().observe(this) { destination ->
            if(destination == 0) {
                return@observe
            }

            navController.navigate(destination)
        }

        viewModel.getUpdating().observe(this) { updating ->
            if(updating) {
                loadingBuilder.create()
            } else {
                loadingBuilder.cancel()
            }
        }

        viewModel.getException().observe(this) { ex ->
            ex?.let {
                Toast.makeText(this@MainActivity, R.string.textError, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getConnection().observe(this) { connection ->
            if(connection) {
                Toast.makeText(this@MainActivity, R.string.textCheckInternet, Toast.LENGTH_SHORT).show()
            }
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

        binding.fab.setOnClickListener {
            val searchView: View = findViewById(R.id.actionSearch)
            searchView.performClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.actionSearch)
        searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.fab.visibility = View.VISIBLE
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.fab.visibility = View.GONE
                return true
            }
        })

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setQuery(newText)
                return true
            }
        })

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val navDestination = navController.currentDestination ?: return false

        when (navDestination.id) {
            R.id.navigationLogin -> {
                disableMenu(menu)
            }

            R.id.navigationRegister -> {
                disableMenu(menu)
            }

            R.id.navigationSearch -> {
                enableMenu(menu)
            }

            R.id.navigationHistory -> {
                disableMenu(menu)
            }

            else -> {
                throw RuntimeException("Unknown Nav: " + navDestination.label)
            }
        }

        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.actionLogout) {
            viewModel.logout()

            return true
        }

        return false
    }

    override fun onBackPressed() {
        val navDestination = navController.currentDestination

        if(navDestination != null && (navDestination.id == R.id.navigationLogin || navDestination.id == R.id.navigationSearch || navDestination.id == R.id.navigationHistory)) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun enableMenu(@NonNull menu: Menu) {
        menu.findItem(R.id.actionSearch).isEnabled = true
        menu.findItem(R.id.actionSearch).isVisible = true
        menu.findItem(R.id.actionLogout).isEnabled = true
        menu.findItem(R.id.actionLogout).isVisible = true
    }

    private fun disableMenu(@NonNull menu: Menu) {
        menu.findItem(R.id.actionSearch).isEnabled = false
        menu.findItem(R.id.actionSearch).isVisible = false
        menu.findItem(R.id.actionLogout).isEnabled = false
        menu.findItem(R.id.actionLogout).isVisible = false
    }

    private fun manageDestination(@NonNull destination: NavDestination) {
        invalidateOptionsMenu()

        if(destination.id == R.id.navigationLogin || destination.id == R.id.navigationRegister || destination.id == R.id.navigationHistory) {
            binding.fab.visibility = View.GONE
        } else {
            binding.fab.visibility = View.VISIBLE
        }

        if(destination.id == R.id.navigationLogin || destination.id == R.id.navigationRegister) {
            binding.bottomNavigationView.visibility = View.GONE
        } else {
            binding.bottomNavigationView.visibility = View.VISIBLE
        }

        viewModel.setQuery("")
    }
}
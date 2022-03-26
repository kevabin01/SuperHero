package com.codequark.superhero.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.codequark.superhero.R
import com.codequark.superhero.databinding.ActivityMainBinding
import com.codequark.superhero.managers.NetworkManager.NetworkStateDef
import com.codequark.superhero.retrofit.requests.SearchRequest
import com.codequark.superhero.utils.Constants
import com.codequark.superhero.utils.LogUtils
import com.codequark.superhero.viewModels.NetworkViewModel
import com.codequark.superhero.viewModels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
            request.execute(params)
        }
    }
}
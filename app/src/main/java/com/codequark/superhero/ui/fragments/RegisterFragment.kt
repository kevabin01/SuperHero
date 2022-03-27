package com.codequark.superhero.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codequark.superhero.R
import com.codequark.superhero.databinding.FragmentRegisterBinding
import com.codequark.superhero.managers.NetworkManager.LoginStateDef
import com.codequark.superhero.ui.dialogs.LoadingDialog
import com.codequark.superhero.viewModels.MainViewModel
import com.codequark.superhero.viewModels.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    private lateinit var loadingBuilder: LoadingDialog.Builder

    @NonNull
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingBuilder = LoadingDialog.Builder(requireContext())

        viewModel.getUpdating().observe(viewLifecycleOwner) { updating ->
            if(updating) {
                loadingBuilder.create()
            } else {
                loadingBuilder.cancel()
            }
        }

        viewModel.getLoginState().observe(viewLifecycleOwner) { integer ->
            when (integer) {
                LoginStateDef.STATE_DEFAULT, LoginStateDef.STATE_LOGIN_ERROR_PASSWORD, LoginStateDef.STATE_LOGIN_ERROR_NOT_EXISTS, LoginStateDef.STATE_LOGIN_ERROR_MANY_REQUESTS -> {}

                LoginStateDef.STATE_LOGIN_SUCCESS -> {
                    viewModel.setUpdating(false)

                    viewModel.setDestination(R.id.navigationSearch)
                }

                LoginStateDef.STATE_LOGIN_ERROR -> {
                    viewModel.setUpdating(false)
                    showToast(R.string.textError)
                }

                LoginStateDef.STATE_LOGIN_ERROR_NETWORK -> {
                    viewModel.setUpdating(false)
                    showToast(R.string.textCheckInternet)
                }

                LoginStateDef.STATE_LOGIN_ERROR_USUARIO -> {
                    viewModel.setUpdating(false)
                    showToast(R.string.textUsuarioInvalido)
                }

                LoginStateDef.STATE_LOGIN_ERROR_USUARIO_EMPTY -> {
                    binding.edtEmail.error = text(R.string.textUsuarioVacio)
                    binding.edtEmail.requestFocus()
                }

                LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY -> {
                    binding.edtPassword.error = text(R.string.textPasswordVacio)
                    binding.edtPassword.requestFocus()
                }

                LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LONGITUD -> {
                    binding.edtPassword.error = text(R.string.textErrorCampoLongitud)
                    binding.edtPassword.requestFocus()
                }

                LoginStateDef.STATE_LOGIN_ERROR_EXISTS -> {
                    viewModel.setUpdating(false)
                    showToast(R.string.textUsuarioExiste)
                }
            }
        }
    }

    private fun showToast(@StringRes resource: Int) {
        Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()
    }

    private fun extractText(@NonNull view: TextInputEditText): String {
        val editable = view.text ?: return ""
        return editable.toString()
    }

    private fun text(@StringRes key: Int): String {
        return resources.getString(key)
    }
}
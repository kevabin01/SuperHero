package com.codequark.superhero.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.codequark.superhero.R;
import com.codequark.superhero.application.AppSettings;
import com.codequark.superhero.databinding.FragmentLoginBinding;
import com.codequark.superhero.managers.NetworkManager.LoginStateDef;
import com.codequark.superhero.models.login.Login;
import com.codequark.superhero.ui.dialogs.LoadingDialog;
import com.codequark.superhero.viewModels.MainViewModel;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    private MainViewModel viewModel;

    private LoadingDialog.Builder loadingBuilder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingBuilder = new LoadingDialog.Builder(requireContext());

        viewModel.getLoginState().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                @LoginStateDef
                final int loginState = integer;

                switch (loginState) {
                    case LoginStateDef.STATE_DEFAULT: {
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_SUCCESS: {
                        viewModel.setUpdating(false);

                        //viewModel.setDestination(R.id.navigationListas);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textError);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_NETWORK: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textCheckInternet);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_USUARIO: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textUsuarioInvalido);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_PASSWORD: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textPasswordInvalido);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_USUARIO_EMPTY: {
                        binding.edtUsuario.setError(text(R.string.textUsuarioVacio));
                        binding.edtUsuario.requestFocus();
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_EMPTY: {
                        binding.edtPassword.setError(text(R.string.textPasswordVacio));
                        binding.edtPassword.requestFocus();
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_PASSWORD_LONGITUD: {
                        binding.edtPassword.setError(text(R.string.textErrorCampoLongitud));
                        binding.edtPassword.requestFocus();
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_EXISTS: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textUsuarioExiste);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_NOT_EXISTS: {
                        viewModel.setUpdating(false);

                        showToast(R.string.textUsuarioNoExiste);
                        break;
                    }

                    case LoginStateDef.STATE_LOGIN_ERROR_MANY_REQUESTS: {
                        viewModel.setUpdating(false);
                        showToast(R.string.textErrorLoginDemasiadosIntentos);

                        binding.edtUsuario.setText("");
                        binding.edtPassword.setText("");
                        break;
                    }
                }
            }
        });

        viewModel.getUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean updating) {
                if(updating) {
                    loadingBuilder.create();
                } else {
                    loadingBuilder.cancel();
                }
            }
        });

        final Login login = AppSettings.Companion.getLogin();

        if(login != null) {
            viewModel.setLoginState(LoginStateDef.STATE_LOGIN_SUCCESS);
        }
    }

    @NonNull
    private String text(@StringRes int key) {
        return getResources().getString(key);
    }

    private void showToast(@StringRes int resource) {
        Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show();
    }
}
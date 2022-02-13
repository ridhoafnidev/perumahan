package com.ridhoafnidev.project.feature.auth.login

import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.google.android.material.snackbar.Snackbar
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.*
import com.ridhoafnidev.project.feature.auth.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.FragmentLoginBinding
import com.ridhoafnidev.project.feature_auth.databinding.LayoutFormLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), ModuleNavigator {

    private val formBinding: LayoutFormLoginBinding
        get() = binding.layoutFormLogin

    private val textHintEmptyEmailPhoneNumber by lazy {
        getString(R.string.username)
    }

    private val textHintEmptyPassword by lazy {
        getString(R.string.required_password)
    }

    private val authViewModel by viewModel<AuthViewModel>()

    override fun initView() {
        authViewModel.login.observe(viewLifecycleOwner){ auth ->
            when(auth){
                is ApiEvent.OnProgress -> {
                    formBinding.btnLogin.showProgress()
                }
                is ApiEvent.OnSuccess -> {
                    formBinding.btnLogin.hideProgress()
                    navigateToHomeActivity()
                }
                is ApiEvent.OnFailed -> {
                    formBinding.btnLogin.hideProgress()
                    showSnackBar(requireContext(), binding.parentLogin, "Username atau Password salah!", Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    override fun initListener() {
        with(binding.layoutFormLogin) {
            form {
                useRealTimeValidation(disableSubmit = true)
                inputLayout(R.id.input_layout_email_or_number_phone) {
                    isNotEmpty().description(textHintEmptyEmailPhoneNumber)
                }
                inputLayout(R.id.input_layout_password) {
                    isNotEmpty().description(textHintEmptyPassword)
                }
                submitWith(R.id.btn_login) {
                    dismissKeyboard()

                    authViewModel.username = edtEmailOrNumberPhone.text.toString()
                    authViewModel.password = edtPassword.text.toString()

                    authViewModel.login()
                }
            }
            btnLogin.bindLifecycle(viewLifecycleOwner)
        }

        binding.btnBuatAkun.setOnClickListener {
            val toRegisterFragment = LoginFragmentDirections
                .actionLoginFragmentToRegisterFragment()
            findNavController()
                .navigate(toRegisterFragment)
        }
    }
}
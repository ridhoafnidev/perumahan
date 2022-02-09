package com.ridhoafnidev.project.feature.auth.login

import com.afollestad.vvalidator.form
import com.google.android.material.snackbar.Snackbar
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.*
import com.ridhoafnidev.project.feature.auth.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), ModuleNavigator {

    private val textBtnLogin by lazy {
        getString(R.string.text_login)
    }

    private val textHintEmptyEmailPhoneNumber by lazy {
        getString(R.string.requeired_email_or_phone_number)
    }

    private val textHintEmptyPassword by lazy {
        getString(R.string.required_password)
    }

    private val authViewModel by viewModel<AuthViewModel>()

    override fun initView() {
        authViewModel.login.observe(viewLifecycleOwner){ auth ->
            when(auth){
                is ApiEvent.OnProgress -> {
                    showProgress()
                }
                is ApiEvent.OnSuccess -> {
                    hideProgress(true)
                    navigateToHomeActivity()
                }
                is ApiEvent.OnFailed -> {
                    hideProgress(true)

                    showSnackBar(requireContext(), binding.parentLogin, "errorMessage", Snackbar.LENGTH_LONG)
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
    }

    private fun showProgress() = with(binding.layoutFormLogin) {
        listOf(
            btnLogin, inputLayoutEmailOrNumberPhone, inputLayoutPassword
        ).forEach { it.isEnabled = false }
        btnLogin.showProgress()
    }

    private fun hideProgress(isEnable: Boolean) = with(binding.layoutFormLogin){
        btnLogin.postDelayed(
            {
                listOf(
                    btnLogin, edtEmailOrNumberPhone, edtPassword, inputLayoutEmailOrNumberPhone,
                    inputLayoutPassword
                ).forEach { it.isEnabled = true }
            },1000L
        )

        btnLogin.hideProgress(textBtnLogin){
            isEnable && with(binding.layoutFormLogin) {
                "${edtEmailOrNumberPhone.text}".isNotBlank() && "${edtPassword.text}".isNotBlank()
            }
        }
    }

}
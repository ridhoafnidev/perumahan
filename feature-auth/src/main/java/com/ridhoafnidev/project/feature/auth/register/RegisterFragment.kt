package com.ridhoafnidev.project.feature.auth.register

import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.vvalidator.form
import com.google.android.material.textfield.TextInputEditText
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.bindLifecycle
import com.ridhoafnidev.project.core_util.dismissKeyboard
import com.ridhoafnidev.project.core_util.hideProgress
import com.ridhoafnidev.project.core_util.showProgress
import com.ridhoafnidev.project.feature.auth.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.FragmentRegisterBinding
import com.ridhoafnidev.project.feature_auth.databinding.LayoutFormRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val formBinding: LayoutFormRegisterBinding
        get() = binding.layoutFormRegister

    private val authViewModel: AuthViewModel by viewModel()

    override fun initView() {
        observeRegisterResponse()
    }

    override fun initListener() {
        val formHints = resources.getStringArray(R.array.form_hints)
        formBinding.apply {
            form {
                useRealTimeValidation(disableSubmit = true)
                arrayOf(
                    inputLayoutNamaLengkap,
                    inputLayoutAlamat,
                    inputLayoutUsername,
                    inputLayoutPassword,
                    inputLayoutEmail,
                    inputLayoutNoHp
                ).forEachIndexed { index, textInputLayout ->
                    inputLayout(textInputLayout) {
                        isNotEmpty()
                            .description(getString(R.string.message_error_field, formHints[index]))
                    }
                }
                submitWith(btnRegister) {
                    dismissKeyboard()

                    authViewModel.register(
                        username = edtUsername.toText(),
                        password = edtPassword.toText(),
                        namaLengkap = edtNamaLengkap.toText(),
                        alamat = edtAlamat.toText(),
                        noHp = edtNoHp.toText(),
                        email = edtEmail.toText()
                    )
                }

                btnRegister.bindLifecycle(viewLifecycleOwner)
            }
        }
    }

    private fun observeRegisterResponse() {
        authViewModel.register.observe(requireActivity()) { apiEvent ->
            formBinding.apply {
                when (apiEvent) {
                    is ApiEvent.OnProgress -> btnRegister.showProgress()
                    is ApiEvent.OnSuccess -> {
                        btnRegister.hideProgress()
                        Toast.makeText(context, R.string.message_success_register, Toast.LENGTH_SHORT).show()
                    }
                    is ApiEvent.OnFailed -> {
                        btnRegister.hideProgress()
                        Toast.makeText(context, "Registrasi gagal, silahkan coba lagi", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun TextInputEditText.toText(): String =
        text.toString().trim()
}
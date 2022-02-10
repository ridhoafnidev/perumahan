package com.ridhoafnidev.project.feature.detail_perumahan

import android.widget.Toast
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentCheckoutBinding
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModel()

    override fun initView() {
        getCurrentUser()
        setCurrentUser()
        setToolbarTitle()
    }

    override fun initListener() {
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(), "Checkout berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentUser() {
        authViewModel.getCurrentUser()
    }

    private fun setCurrentUser() {
        authViewModel.currentUser.observe(requireActivity()) { currentUser ->
            if (currentUser != null) {
                setFormCheckout(currentUser)
            }
        }
    }

    private fun setToolbarTitle() {
        (activity as BaseActivity<*>)
            .setPageName(getString(R.string.title_checkout_perumahan))
    }

    private fun setFormCheckout(auth: Auth) {
        if (auth.role != "konsumen") {
            binding.btnSubmit.isEnabled = false
        }
        binding.apply {
            edtNamaLengkap.setText(auth.namaLengkap)
            edtAlamat.setText(auth.alamat)
            edtNoHp.setText(auth.noHp)
            edtEmail.setText(auth.email)
        }
    }
}
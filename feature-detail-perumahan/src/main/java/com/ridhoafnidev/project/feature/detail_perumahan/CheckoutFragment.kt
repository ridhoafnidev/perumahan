package com.ridhoafnidev.project.feature.detail_perumahan

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.fragment.navArgs
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.PerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentCheckoutBinding
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.CheckoutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModel()
    private val checkoutViewModel: CheckoutViewModel by viewModel()

    private val arguments: CheckoutFragmentArgs by navArgs()

    override fun initView() {
        getCurrentUser()
        getPerumahan()
        setCurrentUser()
        setPerumahan()
        setToolbarTitle(R.string.title_checkout_perumahan)
    }

    override fun initListener() {
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(), "Checkout berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentUser() {
        authViewModel.getCurrentUser()
    }

    private fun getPerumahan() {
        checkoutViewModel.getListPerumahan(arguments.tipePerumahanId)
    }

    private fun setCurrentUser() {
        authViewModel.currentUser.observe(requireActivity()) { currentUser ->
            if (currentUser != null) {
                setFormCheckout(currentUser)
            }
        }
    }

    private fun setPerumahan() {
        checkoutViewModel.listPerumahan.observe(requireActivity()) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    val listPerumahan = apiEvent.getData().map {
                        it.namaPerumahan
                    }
                    setupEdtPerumahan(listPerumahan)
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setToolbarTitle(@StringRes title: Int) {
        (activity as BaseActivity<*>)
            .setPageName(getString(title))
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
            edtPerumahan.setText(arguments.namaPerumahan)
            edtTipeRumah.setText(arguments.tipePerumahan)
        }
    }

    private fun setupEdtPerumahan(listPerumahan: List<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_nama_perumahan, R.id.tv_item_nama_perumahan, listPerumahan)
        binding.edtPerumahan.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setToolbarTitle(R.string.title_detail_perumahan)
    }
}
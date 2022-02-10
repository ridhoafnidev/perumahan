package com.ridhoafnidev.project.feature.detail_perumahan

import android.widget.Toast
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentCheckoutBinding

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    override fun initView() {
        (activity as BaseActivity<*>)
            .setPageName(getString(R.string.title_checkout_perumahan))
    }

    override fun initListener() {
        binding.btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(), "Checkout berhasil", Toast.LENGTH_SHORT).show()
        }
    }
}
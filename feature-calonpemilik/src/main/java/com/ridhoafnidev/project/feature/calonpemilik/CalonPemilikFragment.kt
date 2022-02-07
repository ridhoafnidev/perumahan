package com.ridhoafnidev.project.feature.calonpemilik

import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.CalonPemilik
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.ridhoafnidev.project.core_navigation.ActionType
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentCalonPemilikBinding
import com.ridhoafnidev.project.feature.calonpemilik.viewmodel.CalonPemilikViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalonPemilikFragment : BaseFragment<FragmentCalonPemilikBinding>(FragmentCalonPemilikBinding::inflate) {

    private val calonPemilikViewModel: CalonPemilikViewModel by viewModel()

    override fun initView() {
        getCalonPemilik()
        setupCalonPemilik()
    }

    override fun initListener() {

    }

    private fun getCalonPemilik() {
        calonPemilikViewModel
            .getCalonPemilikAll()
    }

    private fun setupCalonPemilik() {
        calonPemilikViewModel.listCalonPemilik.observe(requireActivity()) { calonPemilikEvent ->
            when (calonPemilikEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    setupRvCalonPemilik(calonPemilikEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupRvCalonPemilik(listCalonPemilik: ListCalonPemilik) {
        val dataSource = dataSourceTypedOf(listCalonPemilik)
        binding.rvCalonPemilik.setup {
            withDataSource(dataSource)
            withItem<CalonPemilik, CalonPemilikViewHolder>(R.layout.item_calon_pemilik) {
                onBind(::CalonPemilikViewHolder) { _, item ->
                    tvNamaCalonPemilik.text = item.nama
                    tvAlamatCalonPemilik.text = item.alamat
                    tvTipeRumahCalonPemilik.text = item.tipeRumah
                    chipStatusCalonPemilik.text = item.statusPengajuan
                }
                onClick {
                    val toAddCalonPemilikFragment = CalonPemilikFragmentDirections
                        .actionCalonPemilikFragmentToAddCalonPemilikFragment(ActionType.Edit)
                    findNavController().navigate(toAddCalonPemilikFragment)
                }
            }
        }
    }
}
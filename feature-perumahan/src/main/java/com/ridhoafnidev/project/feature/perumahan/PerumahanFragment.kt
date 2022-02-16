package com.ridhoafnidev.project.feature.perumahan

import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.ListTipePerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.TipePerumahanGetAll
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.perumahan.databinding.FragmentPerumahanBinding
import com.ridhoafnidev.project.feature.perumahan.viewholder.PerumahanViewHolder
import com.ridhoafnidev.project.feature.perumahan.viewmodel.PerumahanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PerumahanFragment : BaseFragment<FragmentPerumahanBinding>(FragmentPerumahanBinding::inflate), 
    ModuleNavigator {
    
    private val perumahanViewModel: PerumahanViewModel by viewModel()
    
    override fun initView() {
        getTipePerumahan()
        setupTipePerumahan()
    }

    override fun initListener() {
        
    }

    private fun getTipePerumahan() {
        perumahanViewModel.tipeRumahGetAll()
    }

    private fun setupTipePerumahan() {
        perumahanViewModel.listTipeRumah.observe(requireActivity()) { listEvent ->
            when (listEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    setupRvPerumahan(listEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupRvPerumahan(listEvent: ListTipePerumahanGetAll) {
        binding.rvPerumahan.setup {
            withDataSource(dataSourceOf(listEvent))
            withItem<TipePerumahanGetAll, PerumahanViewHolder>(R.layout.item_perumahan){
                onBind(::PerumahanViewHolder){_, item ->
                    tvTipePerumahan.text = item.namaTipe
                    tvUkuranPerumahan.text = getString(R.string.ukuran, item.ukuran)
                    tvTotalPerumahan.text = getString(R.string.total, item.jumlahUnit)
                    tvHargaPerumahan.text = getString(R.string.harga, item.harga)

                    if (item.perumahan.isNotEmpty()) {
                        tvNamaPerumahan.text = item.perumahan[0].namaPerumahan
                    } else {
                        tvHargaPerumahan.text = "-"
                    }

                    if (item.foto.isNotEmpty()) {
                        com.bumptech.glide.Glide.with(requireActivity())
                            .load(com.ridhoafnidev.project.core_data.data.APP_TIPE_PERUMAHAN_PHOTO_URL + item.foto[0].foto)
                            .into(ivPhotoPerumahan)
                    }
                }
                onClick {
                    navigateToDetailPerumahActivity(
                        extras = androidx.core.os.bundleOf(com.ridhoafnidev.project.core_navigation.EXTRA_PERUMAHAN_ID to item.id)
                    )
                }
            }
        }
    }
}
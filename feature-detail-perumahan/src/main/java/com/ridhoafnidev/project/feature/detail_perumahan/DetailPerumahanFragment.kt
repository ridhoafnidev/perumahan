package com.ridhoafnidev.project.feature.detail_perumahan

import androidx.navigation.fragment.findNavController
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_data.data.APP_TIPE_PERUMAHAN_PHOTO_URL
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.PerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import com.ridhoafnidev.project.core_navigation.EXTRA_PERUMAHAN_ID
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentDetailPerumahanBinding
import com.ridhoafnidev.project.feature.detail_perumahan.viewholder.DetailPerumahanViewHolder
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.DetailPerumahanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPerumahanFragment : BaseFragment<FragmentDetailPerumahanBinding>(FragmentDetailPerumahanBinding::inflate) {

    private var tipePerumahId: Int? = 0
    private val detailPerumahanViewModel: DetailPerumahanViewModel by viewModel()

    private var namaPerumahan = ""
    private var tipePerumahan = ""

    override fun initView() {
        tipePerumahId = activity?.intent?.getIntExtra(EXTRA_PERUMAHAN_ID, 0)

        getDetailPerumahan()
        observeDetailPerumahan()
    }

    override fun initListener() {
        binding.btnCheckout.setOnClickListener {
            tipePerumahId?.let {
                val toCheckoutFragment = DetailPerumahanFragmentDirections
                    .actionDetailPerumahanFragmentToCheckoutFragment(
                        tipePerumahan,
                        it,
                        namaPerumahan
                    )
                findNavController().navigate(toCheckoutFragment)
            }
        }
    }

    private fun getDetailPerumahan() {
        tipePerumahId?.let {
            detailPerumahanViewModel.getDetailTipeRumah(it)
        }
    }

    private fun observeDetailPerumahan() {
        detailPerumahanViewModel.detailTipeRumah.observe(requireActivity()) { detailPerumahanEvent ->
            when (detailPerumahanEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    val detailPerumahan = detailPerumahanEvent.getData()
                    setupDetailPerumahan(detailPerumahan)
                    setupRvPerumahan(detailPerumahan.perumahan)
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupDetailPerumahan(perumahan: DetailTipeRumah) {
        binding.apply {
            val unit = "${perumahan.jumlahUnit} Unit"
            chipJumlahPerumahan.text = unit

            val perumahanItem = if (perumahan.perumahan.isNotEmpty()) perumahan.perumahan[0] else null
            namaPerumahan = perumahanItem?.namaPerumahan ?: "-"
            tipePerumahan = perumahan.namaTipe

            tvNamaPerumahan.text = perumahanItem?.namaPerumahan ?: "-"

            tvNamaTipePerumahan.text = perumahan.namaTipe
            tvHargaPerumahan.text = getString(R.string.harga, perumahan.harga)

            tbUkuran.text = getString(R.string.table_body_ukuran, perumahan.ukuran)
            tbPondasi.text = getString(R.string.table_body, perumahan.pondasi)
            tbDinding.text = getString(R.string.table_body, perumahan.dinding)
            tbLantai.text = getString(R.string.table_body, perumahan.lantai)
            tbPlafon.text = getString(R.string.table_body, perumahan.plafon)
            tbPintuDepan.text = getString(R.string.table_body, perumahan.pintuDepan)
            tbDindingKamarMandi.text = getString(R.string.table_body, perumahan.dindingKm)
            tbKusen.text = getString(R.string.table_body, perumahan.kusen)
            tbRangkapAtap.text = getString(R.string.table_body, perumahan.rAtap)
            tbSanitasi.text = getString(R.string.table_body, perumahan.sanitasi)
            tbListrik.text = getString(R.string.table_body, perumahan.listrik)
            tbAir.text = getString(R.string.table_body, perumahan.air)

            val imageList = perumahan.foto.map {
                SlideUIModel(APP_TIPE_PERUMAHAN_PHOTO_URL + it.foto)
            }
            setupPhotoPerumahan(imageList)
        }
    }

    private fun setupRvPerumahan(listPerumahan: List<PerumahanGetAll>) {
        binding.rvPerumahan.setup {
            withDataSource(dataSourceOf(listPerumahan))
            withItem<PerumahanGetAll, DetailPerumahanViewHolder>(R.layout.item_detail_perumahan) {
                onBind(::DetailPerumahanViewHolder) { _, item ->
                    tbNamaPerumahan_.text = item.namaPerumahan
                    tbAlamatPerumahan.text = item.alamat
                    tbLuasTanahPerumahan.text = getString(R.string.satuan_meter, item.luasTanah)
                    tbKeteranganPerumahan.text = item.keterangan
                }
            }
        }
    }

    private fun setupPhotoPerumahan(imageList: List<SlideUIModel>) {
        binding.ivPerumahan.apply {
            setImageList(imageList)
            stopSliding()
        }
    }
}
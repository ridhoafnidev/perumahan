package com.ridhoafnidev.project.feature.detail_perumahan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import com.ridhoafnidev.project.core_navigation.EXTRA_PERUMAHAN_ID
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentDetailPerumahanBinding
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.DetailPerumahanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPerumahanFragment : Fragment() {

    private var _binding: FragmentDetailPerumahanBinding? = null
    private val binding: FragmentDetailPerumahanBinding
        get() = _binding!!

    private var perumahanId: Int? = 0
    private val detailPerumahanViewModel: DetailPerumahanViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPerumahanBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        perumahanId = activity?.intent?.getIntExtra(EXTRA_PERUMAHAN_ID, 0)

        getDetailPerumahan()
        observeDetailPerumahan()

        binding.btnCheckout.setOnClickListener {
            val toCheckoutFragment = DetailPerumahanFragmentDirections
                .actionDetailPerumahanFragmentToCheckoutFragment()
            findNavController().navigate(toCheckoutFragment)
        }
    }

    private fun getDetailPerumahan() {
        perumahanId?.let {
            detailPerumahanViewModel.getDetailTipeRumah(it)
        }
    }

    private fun observeDetailPerumahan() {
        detailPerumahanViewModel.detailTipeRumah.observe(requireActivity()) { detailPerumahanEvent ->
            when (detailPerumahanEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    setupDetailPerumahan(detailPerumahanEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupDetailPerumahan(perumahan: DetailTipeRumah) {
        binding.apply {
            val unit = "${perumahan.jumlahUnit} Unit"
            chipJumlahPerumahan.text = unit

            tvNamaTipePerumahan.text = perumahan.namaTipe
            tvHargaPerumahan.text = getString(R.string.harga, perumahan.harga.toString())

            tbUkuran.text = getString(R.string.table_body_ukuran, perumahan.ukuran.toString())
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

//            Glide.with(this@DetailPerumahanFragment)
//                .load()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
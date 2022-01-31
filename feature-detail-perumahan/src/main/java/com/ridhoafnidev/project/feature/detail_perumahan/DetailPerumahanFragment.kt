package com.ridhoafnidev.project.feature.detail_perumahan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ridhoafnidev.project.core_domain.model.TipeRumah
import com.ridhoafnidev.project.core_navigation.EXTRA_PERUMAHAN_ID
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentDetailPerumahanBinding

class DetailPerumahanFragment : Fragment() {

    private var _binding: FragmentDetailPerumahanBinding? = null
    private val binding: FragmentDetailPerumahanBinding
        get() = _binding!!

    private var perumahanId: String? = null

    private val dummyPerumahan by lazy {
        TipeRumah(
            id = "P1",
            namaTipe = "Enau - Perumahan Citra 1",
            ukuran = 1000,
            pondasi = "Semen",
            dinding = "Semen",
            lantai = "Granit",
            plafon = "Sunda Plafon",
            pintuDepan = "Baja ringan",
            dindingKamarMandi = "Granit",
            kusen = "Baja",
            rangkapAtap = "Baja ringan",
            atap = "Seng",
            sanitasi = "Air",
            listrik = "PLTA",
            air = "Sumur Bor",
            harga = 1000000,
            jumlahUnit = 100,
            photo = ""
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPerumahanBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        perumahanId = activity?.intent?.getStringExtra(EXTRA_PERUMAHAN_ID)

        setupDetailPerumahan(dummyPerumahan)

        binding.btnCheckout.setOnClickListener {
            val toCheckoutFragment = DetailPerumahanFragmentDirections
                .actionDetailPerumahanFragmentToCheckoutFragment()
            findNavController().navigate(toCheckoutFragment)
        }
    }

    private fun setupDetailPerumahan(perumahan: TipeRumah) {
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
            tbDindingKamarMandi.text = getString(R.string.table_body, perumahan.dindingKamarMandi)
            tbKusen.text = getString(R.string.table_body, perumahan.kusen)
            tbRangkapAtap.text = getString(R.string.table_body, perumahan.rangkapAtap)
            tbSanitasi.text = getString(R.string.table_body, perumahan.sanitasi)
            tbListrik.text = getString(R.string.table_body, perumahan.listrik)
            tbAir.text = getString(R.string.table_body, perumahan.air)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.ridhoafnidev.project.feature.calonpemilik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_domain.model.CalonPemilik
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentCalonPemilikBinding
import java.util.ArrayList

class CalonPemilikFragment : Fragment() {

    private var _binding: FragmentCalonPemilikBinding? = null
    private val binding: FragmentCalonPemilikBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalonPemilikBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCalonPemilik = arrayListOf<CalonPemilik>()

        (1..10).forEach {
            listCalonPemilik.add(
                CalonPemilik(
                    "Nama ke $it",
                    "Jl. Merbau $it",
                    "Belum Dihubungi",
                    "Enau - Perumahan Citra $it"
                )
            )
        }

        setupRvCalonPemilik(listCalonPemilik)
    }

    private fun setupRvCalonPemilik(listCalonPemilik: ArrayList<CalonPemilik>) {
        val dataSource = dataSourceTypedOf(listCalonPemilik)
        binding.rvCalonPemilik.setup {
            withDataSource(dataSource)
            withItem<CalonPemilik, CalonPemilikViewHolder>(R.layout.item_calon_pemilik) {
                onBind(::CalonPemilikViewHolder) { _, item ->
                    tvNamaCalonPemilik.text = item.namaLengkap
                    tvAlamatCalonPemilik.text = item.alamat
                    tvTipeRumahCalonPemilik.text = item.tipeRumah
                    chipStatusCalonPemilik.text = item.status
                }
                onClick {
                    val toAddCalonPemilikFragment = CalonPemilikFragmentDirections
                        .actionCalonPemilikFragmentToAddCalonPemilikFragment()
                    findNavController().navigate(toAddCalonPemilikFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
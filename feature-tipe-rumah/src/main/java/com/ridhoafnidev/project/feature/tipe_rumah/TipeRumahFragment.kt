package com.ridhoafnidev.project.feature.tipe_rumah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_domain.model.TipeRumah
import com.ridhoafnidev.project.feature.tipe_rumah.databinding.FragmentTipeRumahBinding

class TipeRumahFragment : Fragment() {

    private var _binding: FragmentTipeRumahBinding? = null
    private val binding: FragmentTipeRumahBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTipeRumahBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyTipeRumah = arrayListOf<TipeRumah>()

        (1..10).forEach {
            dummyTipeRumah.add(
                TipeRumah("Rumah tipe $it", it+100)
            )
        }

        setupRecyclerTipeRumah(dummyTipeRumah)

        binding.btnTambahTipeRumah.setOnClickListener {
            val toAddTipeRumah = TipeRumahFragmentDirections.actionTipeRumahFragmentToAddTipeRumahFragment()
            findNavController().navigate(toAddTipeRumah)
        }
    }

    private fun setupRecyclerTipeRumah(listTipeRumah: List<TipeRumah>) {
        val dataSource = dataSourceTypedOf(listTipeRumah)
        binding.rvTipeRumah.setup {
            withDataSource(dataSource)
            withItem<TipeRumah, TipeRumahViewHolder>(R.layout.item_tipe_rumah) {
                onBind(::TipeRumahViewHolder) { _, item ->
                    tvNamaTipe.text = item.namaTipe
                    tvUkuran.text = item.ukuran.toString()

                    btnEdit.setOnClickListener {
                        Toast.makeText(requireContext(), "Edit tipe tumah", Toast.LENGTH_SHORT)
                            .show()
                    }
                    btnDelete.setOnClickListener {
                        Toast.makeText(requireContext(), "Delete tipe tumah", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                onClick {
                    Toast.makeText(requireContext(), "Detail tipe rumah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
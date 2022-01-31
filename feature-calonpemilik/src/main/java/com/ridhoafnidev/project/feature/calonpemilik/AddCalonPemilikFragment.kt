package com.ridhoafnidev.project.feature.calonpemilik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.ridhoafnidev.project.core_domain.model.CalonPemilik
import com.ridhoafnidev.project.core_navigation.ActionType
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentAddCalonPemilikBinding

class AddCalonPemilikFragment : Fragment() {

    private var _binding: FragmentAddCalonPemilikBinding? = null
    private val binding: FragmentAddCalonPemilikBinding
        get() = _binding!!

    private lateinit var actionType: ActionType
    private val args by navArgs<AddCalonPemilikFragmentArgs>()

    private val dummyCalonPemilik by lazy {
        CalonPemilik(
            namaLengkap = "Hitler",
            alamat = "Jl. Merbau",
            status = "Belum Dihubungi",
            tipeRumah = "Enau - Perumahan Citra",
            noHp = "0902390213901",
            email = "fwfw@gmail.com",
            perumahan = "Perumahan"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCalonPemilikBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionType = args.actionType

        when (actionType) {
            ActionType.Add -> {

            }
            ActionType.Edit -> {
                setupFormAddCalonPemilik(dummyCalonPemilik)
            }
            else -> {}
        }

        val dummyPerumahan = resources.getStringArray(R.array.dummy_perumahan)
        setupEdtPerumahan(dummyPerumahan)
        val dummyStatus = resources.getStringArray(R.array.dummy_status)
        setupEdtStatus(dummyStatus)
    }

    private fun setupFormAddCalonPemilik(calonPemilik: CalonPemilik) {
        binding.apply {
            edtNamaLengkap.setText(calonPemilik.namaLengkap)
            edtAlamat.setText(calonPemilik.alamat)
            edtNoHp.setText(calonPemilik.noHp)
            edtEmail.setText(calonPemilik.email)
            edtPerumahan.setText(calonPemilik.perumahan)
            edtStatus.setText(calonPemilik.status)
            edtTipeRumah.setText(calonPemilik.tipeRumah)
        }

        binding.apply {
            arrayOf(
                edtNamaLengkap, edtAlamat, edtNoHp,
                edtEmail, tilPerumahan, edtTipeRumah
            ).forEach { editText ->
                editText.alpha = 0.5f
                editText.isEnabled = false
            }
        }
    }

    private fun setupEdtPerumahan(listPerumahan: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_perumahan, listPerumahan)
        binding.edtPerumahan.setAdapter(adapter)
    }

    private fun setupEdtStatus(listStatus: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_perumahan, listStatus)
        binding.edtStatus.setAdapter(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
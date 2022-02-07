package com.ridhoafnidev.project.feature.calonpemilik

import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ridhoafnidev.project.core_data.data.APP_BUKTI_TRANSFER_PHOTO_URL
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentEditCalonPemilikBinding
import com.ridhoafnidev.project.feature.calonpemilik.viewmodel.CalonPemilikViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCalonPemilikFragment : BaseFragment<FragmentEditCalonPemilikBinding>(FragmentEditCalonPemilikBinding::inflate) {

    private val args: EditCalonPemilikFragmentArgs by navArgs()
    private val calonPemilikID: Int
        get() = args.calonPemilikID

    private val calonPemilikViewModel: CalonPemilikViewModel by viewModel()

    override fun initView() {
        val dummyStatus = resources.getStringArray(R.array.dummy_status)
        setupEdtStatus(dummyStatus)

        getDetailCalonPemilik(calonPemilikID)
        setDetailCalonPemilik()
    }

    override fun initListener() {

    }

    private fun getDetailCalonPemilik(id: Int) {
        calonPemilikViewModel.getDetailCalonPemilik(id)
    }

    private fun setDetailCalonPemilik() {
        calonPemilikViewModel.detailCalonPemilik.observe(requireActivity()) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    setupFormAddCalonPemilik(apiEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupFormAddCalonPemilik(calonPemilik: DetailCalonPemilik) {
        binding.apply {
            edtNamaLengkap.setText(calonPemilik.konsumenNama)
            edtAlamat.setText(calonPemilik.konsumenAlamat)
            edtNoHp.setText(calonPemilik.konsumenNoHp)
            edtEmail.setText(calonPemilik.konsumenEmail)
            edtPerumahan.setText(calonPemilik.perumahan)
            edtStatus.setText(calonPemilik.statusPengajuan)
            edtTipeRumah.setText(calonPemilik.tipeRumah)

            val photoUrl = APP_BUKTI_TRANSFER_PHOTO_URL + calonPemilik.buktiTransfer

            Glide.with(requireActivity())
                .load(photoUrl)
                .into(btnBuktiDp)

            binding.btnBuktiDp.setOnClickListener {
                val toPreviewDPActivity = EditCalonPemilikFragmentDirections
                    .actionEditCalonPemilikFragmentToPreviewDPActivity(photoUrl)
                findNavController()
                    .navigate(toPreviewDPActivity)
            }
        }
    }

    private fun setupEdtStatus(listStatus: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_perumahan, listStatus)
        binding.edtStatus.setAdapter(adapter)
    }
}
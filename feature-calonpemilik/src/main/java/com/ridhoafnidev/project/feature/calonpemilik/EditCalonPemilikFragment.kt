package com.ridhoafnidev.project.feature.calonpemilik

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ridhoafnidev.project.core_data.data.APP_BUKTI_TRANSFER_PHOTO_URL
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.ListStatusPengajuan
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentEditCalonPemilikBinding
import com.ridhoafnidev.project.feature.calonpemilik.viewmodel.CalonPemilikViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCalonPemilikFragment : BaseFragment<FragmentEditCalonPemilikBinding>(FragmentEditCalonPemilikBinding::inflate) {

    private val args: EditCalonPemilikFragmentArgs by navArgs()
    private val calonPemilikID: Int
        get() = args.calonPemilikID

    private val calonPemilikViewModel: CalonPemilikViewModel by viewModel()

    private lateinit var listStatusPengajuan: ListStatusPengajuan

    override fun initView() {
        setTitleByRole()
        getDetailCalonPemilik(calonPemilikID)
        getStatusPengajuan()
        setDetailCalonPemilik()
        setStatusPengajuan()
        setUpdateStatusPengajuan()
    }

    override fun initListener() {
        binding.btnSubmit.setOnClickListener {
            if (args.auth.role != "konsumen") {
                val selectedStatusPengajuan = binding.edtStatus.text.toString()
                val statusPengajuan = listStatusPengajuan.find {
                    it.nama == selectedStatusPengajuan
                }
                if (statusPengajuan != null) {
                    calonPemilikViewModel.updateStatusPengajuan(
                        calonPemilikID,
                        statusPengajuan
                    )
                }
            } else {
                Toast.makeText(requireContext(), "Pengajuan diproses", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setTitleByRole() {
        if (args.auth.role == "konsumen") {
            setTitleName(R.string.title_detail_checkout_perumahan)
        } else {
            setTitleName(R.string.title_edit_calon_pemilik)
        }
    }

    private fun getDetailCalonPemilik(id: Int) {
        calonPemilikViewModel.getDetailCalonPemilik(id)
    }

    private fun getStatusPengajuan() {
        if (args.auth.role != "konsumen") {
            calonPemilikViewModel.getStatusPengajuanAll()
        }
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

    private fun setStatusPengajuan() {
        calonPemilikViewModel.listStatusPengajuan.observe(requireActivity()) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {
                    binding.btnSubmit.isEnabled = false
                }
                is ApiEvent.OnSuccess -> {
                    listStatusPengajuan = apiEvent.getData()
                    val listStringStatusPengajuan = listStatusPengajuan.map {
                        it.nama
                    }
                    setupEdtStatus(listStringStatusPengajuan)
                    binding.btnSubmit.isEnabled = true
                }
                is ApiEvent.OnFailed -> {
                    binding.btnSubmit.isEnabled = true
                }
            }
        }
    }

    private fun setUpdateStatusPengajuan() {
        calonPemilikViewModel.updateStatusPengajuan.observe(requireActivity()) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {
                    binding.btnSubmit.isEnabled = false
                }
                is ApiEvent.OnSuccess -> {
                    binding.btnSubmit.isEnabled = true
                    Snackbar.make(requireView(), apiEvent.getData().message, Snackbar.LENGTH_SHORT)
                        .show()
                }
                is ApiEvent.OnFailed -> {
                    binding.btnSubmit.isEnabled = true
                    Snackbar.make(requireView(), "Gagal mengupdate status pengajuan!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setupFormAddCalonPemilik(calonPemilik: DetailCalonPemilik) {
        binding.apply {

            if (args.auth.role == "konsumen") {
                edtStatus.isEnabled = false
                edtStatus.alpha = 0.8f
                btnSubmit.text = getString(R.string.cancel)
            }

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

    private fun setupEdtStatus(listStatus: List<String>) {
        val adapter = ArrayAdapter(requireActivity(), R.layout.item_status_pengajuan, R.id.tv_item_status_pengajuan, listStatus)
        binding.edtStatus.setAdapter(adapter)
    }
}
package com.ridhoafnidev.project.feature.perumahan

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.afollestad.vvalidator.form
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.bindLifecycle
import com.ridhoafnidev.project.core_util.dismissKeyboard
import com.ridhoafnidev.project.core_util.hideProgress
import com.ridhoafnidev.project.core_util.showProgress
import com.ridhoafnidev.project.feature.perumahan.databinding.FragmentAddPerumahanBinding
import com.ridhoafnidev.project.feature.perumahan.viewmodel.PerumahanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPerumahanFragment :
    BaseFragment<FragmentAddPerumahanBinding>(FragmentAddPerumahanBinding::inflate),
    ModuleNavigator {

    private val viewModel by viewModel<PerumahanViewModel>()

    private val edtNamaPerumahan by lazy {
        binding.edtNamaPerumahan
    }
    private val edtLuasTanah by lazy {
        binding.edtLuasTanah
    }
    private val edtAlamat by lazy {
        binding.edtAlamat
    }
    private val edtKeterangan by lazy {
        binding.edtKeterangan
    }
    private val btnSubmit by lazy {
        binding.btnSubmit
    }

    private val textHintEmptyNamaPerumahan by lazy {
        getString(R.string.require_nama_perumahan)
    }
    private val textHintEmptyLuasTanah by lazy {
        getString(R.string.require_luas_tanah)
    }
    private val textHintEmptyAlamat by lazy {
        getString(R.string.require_alamat)
    }
    private val textHintEmptyKeterangan by lazy {
        getString(R.string.require_keterangan)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initView() {
        setupInput()
    }

    override fun initListener() {
    }

    private fun setupInput() {
        form {
            useRealTimeValidation(disableSubmit = true)
            inputLayout(R.id.il_nama_perumahan){
                isNotEmpty().description(textHintEmptyNamaPerumahan)
            }
            inputLayout(R.id.il_luas_tanah){
                isNotEmpty().description(textHintEmptyLuasTanah)
            }
            inputLayout(R.id.il_alamat){
                isNotEmpty().description(textHintEmptyAlamat)
            }
            inputLayout(R.id.il_keterangan){
                isNotEmpty().description(textHintEmptyKeterangan)
            }
            submitWith(R.id.btn_submit){
                dismissKeyboard()

                val namaPerumahan = "${edtNamaPerumahan.text}"
                val luasTanah = "${edtLuasTanah.text}"
                val alamat = "${edtAlamat.text}"
                val keterangan = "${edtKeterangan.text}"

                /*
                viewModel.next(eventName, eventLocation, eventDateStart, eventDateEnd, eventTimeStart,
                eventTimeEnd, eventDescription, eventOrganizer, img)

                 */
            }
        }

        btnSubmit.bindLifecycle(viewLifecycleOwner)

    }
}
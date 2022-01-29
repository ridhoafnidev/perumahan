package com.ridhoafnidev.project.feature.perumahan

import android.annotation.SuppressLint
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.afollestad.vvalidator.form
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_util.*
import com.ridhoafnidev.project.feature.perumahan.databinding.FragmentCreateEventBinding
import com.ridhoafnidev.project.feature.perumahan.R as R_create_perumahan
import org.koin.androidx.viewmodel.ext.android.viewModel
class PerumahanFragment : Fragment(), ModuleNavigator {

    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!

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
        getString(R_create_perumahan.string.require_nama_perumahan)
    }
    private val textHintEmptyLuasTanah by lazy {
        getString(R_create_perumahan.string.require_luas_tanah)
    }
    private val textHintEmptyAlamat by lazy {
        getString(R_create_perumahan.string.require_alamat)
    }
    private val textHintEmptyKeterangan by lazy {
        getString(R_create_perumahan.string.require_keterangan)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupInput()
        setupListener()
    }

    private fun setupInput() {
        form {
            useRealTimeValidation(disableSubmit = true)
            inputLayout(R_create_perumahan.id.il_nama_perumahan){
                isNotEmpty().description(textHintEmptyNamaPerumahan)
            }
            inputLayout(R_create_perumahan.id.il_luas_tanah){
                isNotEmpty().description(textHintEmptyLuasTanah)
            }
            inputLayout(R_create_perumahan.id.il_alamat){
                isNotEmpty().description(textHintEmptyAlamat)
            }
            inputLayout(R_create_perumahan.id.il_keterangan){
                isNotEmpty().description(textHintEmptyKeterangan)
            }
            submitWith(R_create_perumahan.id.btn_submit){
                dismissKeyboard()

                showProgress()

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

    private fun setupObserver() {

        viewModel.isSaved.observe(viewLifecycleOwner){ isSaved ->
            if (isSaved){
                Handler(Looper.getMainLooper()).postDelayed({
                    hideProgress(true)
                    navigateToHomeActivity(true)
                }, 1000L)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ClickableViewAccessibility")
    private fun setupListener() {

    }

    private fun showProgress() = with(binding) {
        listOf(
            btnSubmit, ilNamaPerumahan, ilLuasTanah, ilAlamat, ilKeterangan, edtNamaPerumahan,
            edtLuasTanah, edtAlamat, edtKeterangan
        ).forEach { it.isEnabled = false }
        btnSubmit.showProgress()
    }

    private fun hideProgress(isEnable: Boolean = false) = with(binding){
        btnSubmit.postDelayed(
            {
                listOf(
                    btnSubmit, ilNamaPerumahan, ilLuasTanah, ilAlamat, ilKeterangan, edtNamaPerumahan,
                    edtLuasTanah, edtAlamat, edtKeterangan
                ).forEach { it.isEnabled = true }
            },1000L
        )

        btnSubmit.hideProgress(getString(R_create_perumahan.string.submit)){
            isEnable && with(binding) {
                "${edtNamaPerumahan.text}".isNotBlank() && "${edtLuasTanah.text}".isNotBlank()
                && "${edtAlamat.text}".isNotBlank() && "${edtKeterangan.text}".isNotBlank()
            }
        }
    }

}
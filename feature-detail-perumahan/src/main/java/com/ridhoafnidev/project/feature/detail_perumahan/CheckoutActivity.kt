package com.ridhoafnidev.project.feature.detail_perumahan

import android.app.Activity
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.navArgs
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.PerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.ActivityCheckoutBinding
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.CheckoutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CheckoutActivity : BaseActivity<ActivityCheckoutBinding>(ActivityCheckoutBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModel()
    private val checkoutViewModel: CheckoutViewModel by viewModel()

    private val arguments: CheckoutActivityArgs by navArgs()

    private var konsumenId: Int = 0
    private val listPerumahan = mutableListOf<PerumahanGetAll>()

    private lateinit var buktiTransferFile: File

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_checkout_perumahan))

        getCurrentUser()
        getPerumahan()
        setCurrentUser()
        setPerumahan()
        setInsertCalonPemilikResponse()
    }

    override fun initListener() {
        binding.btnBuktiDp.setOnClickListener {
            ImagePicker.with(this)
                .compress(2048)
                .crop()
                .start()
        }
        binding.btnSubmit.setOnClickListener {
            if (validateFormCheckoutPerumahan()) {
                return@setOnClickListener
            }

            val perumahanId = listPerumahan.find {
                it.namaPerumahan == binding.edtPerumahan.text.toString()
            }?.id
            val jumlahDP = binding.edtDp.text.toString().toInt()

            if (perumahanId != null) {
                checkoutViewModel.insertCalonPemilik(
                    konsumenId = konsumenId,
                    tipePerumahanId = arguments.tipePerumahanId,
                    rumahId = perumahanId,
                    jumlahDp = jumlahDP,
                    buktiTransfer = buktiTransferFile
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri = data?.data
                uri?.apply {
                    buktiTransferFile = File(path)
                    binding.btnBuktiDp.setImageURI(uri)
                }
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, "Gagal mengambil gambar, coba lagi!", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(this, "Batal mengambil gambar!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentUser() {
        authViewModel.getCurrentUser()
    }

    private fun getPerumahan() {
        checkoutViewModel.getListPerumahan(arguments.tipePerumahanId)
    }

    private fun setCurrentUser() {
        authViewModel.currentUser.observe(this) { currentUser ->
            if (currentUser != null) {
                setFormCheckout(currentUser)
            }
        }
    }

    private fun setPerumahan() {
        checkoutViewModel.listPerumahan.observe(this) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    listPerumahan.addAll(apiEvent.getData())
                    val listPerumahanNama = listPerumahan.map {
                        it.namaPerumahan
                    }
                    setupEdtPerumahan(listPerumahanNama)
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setInsertCalonPemilikResponse() {
        checkoutViewModel.insertCalonPemilikResponse.observe(this) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {
                    binding.btnSubmit.isEnabled = false
                }
                is ApiEvent.OnSuccess -> {
                    binding.btnSubmit.isEnabled = true
                    Toast.makeText(this, "Checkout Perumahan Berhasil!", Toast.LENGTH_SHORT).show()
                }
                is ApiEvent.OnFailed -> {
                    binding.btnSubmit.isEnabled = true
                    Toast.makeText(this, "Checkout Perumahan Gagal!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFormCheckout(auth: Auth) {
        if (auth.role == "konsumen") {
            konsumenId = auth.konsumenId
            binding.btnSubmit.isEnabled = true
        }
        binding.apply {
            edtNamaLengkap.setText(auth.namaLengkap)
            edtAlamat.setText(auth.alamat)
            edtNoHp.setText(auth.noHp)
            edtEmail.setText(auth.email)
            edtPerumahan.setText(arguments.namaPerumahan)
            edtTipeRumah.setText(arguments.tipePerumahan)
        }
    }

    private fun validateFormCheckoutPerumahan(): Boolean {
        binding.apply {
            arrayOf(
                edtNamaLengkap, edtAlamat,
                edtNoHp, edtEmail,
                edtTipeRumah, edtDp
            ).forEach { editText ->
                if (editText.text?.isEmpty() == true) {
                    editText.error = getString(R.string.field_error, editText.hint)
                    return true
                }
            }

            if (btnBuktiDp.drawable == null) {
                Toast.makeText(this@CheckoutActivity, R.string.image_error, Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return false
    }

    private fun setupEdtPerumahan(listPerumahan: List<String>) {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_nama_perumahan, R.id.tv_item_nama_perumahan, listPerumahan)
        binding.edtPerumahan.setAdapter(arrayAdapter)
    }
}
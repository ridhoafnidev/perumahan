package com.ridhoafnidev.project.feature.simulasi_kpr

import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import com.afollestad.vvalidator.form
import com.ridhoafnidev.project.core_navigation.EXTRA_HARGA_PROPERTI
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.simulasi_kpr.databinding.ActivitySimulasiKprActivityBinding
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class SimulasiKPRActivity : BaseActivity<ActivitySimulasiKprActivityBinding>(ActivitySimulasiKprActivityBinding::inflate) {

    private val hargaProperti: Long
        get() = intent.getIntExtra(EXTRA_HARGA_PROPERTI, 0).toLong()
    private val jumlahPinjaman: BigDecimal
        get() = hargaProperti.toPercent()

    private val listJangkaWaktuPinjaman = (1..30).toList()
    private val listSukuBunga = listOf(
        6.00, 6.68, 7.70, 7.75, 8.00, 9.25, 9.75, 9.99, 10.29, 10.99, 11.29
    )

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_simulasi_kpr))

        setFormSimulasiKPR(hargaProperti)
        setupEdtJangkaWaktuPinjaman(listJangkaWaktuPinjaman)
        setupEdtSukuBunga(listSukuBunga)
    }

    override fun initListener() {
        val formHints = resources.getStringArray(R.array.form_simulasi_hints)
        binding.apply {
            form {
                useRealTimeValidation()
                arrayOf(edtHargaProperty, edtUangMuka, edtJangkaWaktuPinjaman, edtSukuBunga, edtJumlahPinjaman)
                    .forEachIndexed { index, editText ->
                        input(editText) {
                            isNotEmpty().description(getString(
                                R.string.field_error,
                                formHints[index]
                            ))
                        }
                    }

                submitWith(btnSimulasi) {
                    if (it.success()) {
                        val jumlahPinjaman = BigDecimal(edtJumlahPinjaman.text.toString())
                        val jangkaWaktuPinjaman = edtJangkaWaktuPinjaman.text.toString().toInt()
                        val sukuBunga = edtSukuBunga.text.toString().dropLast(1).toDouble()

                        val angsuranPerBulan = jumlahPinjaman
                            .toAngsuranPerbulan(
                                sukuBunga,
                                jangkaWaktuPinjaman
                            )

                        tvAnguranPerbulan.text = angsuranPerBulan.toRupiah()
                    }
                }
            }
        }

        edtHargaPropertyListener()
    }

    private fun edtHargaPropertyListener() {
        binding.edtHargaProperty.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isNotEmpty() == true) {
                    val hargaProperti = p0.toString().toLong()
                    if (hargaProperti >= 10) {
                        val uangMuka = hargaProperti.toPercent(20.0).toString()
                        val jumlahPinjaman = hargaProperti.toPercent().toString()
                        binding.edtUangMuka.setText(uangMuka)
                        binding.edtJumlahPinjaman.setText(jumlahPinjaman)
                    } else {
                        binding.edtUangMuka.setText("")
                        binding.edtJumlahPinjaman.setText("")
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }
        })
    }

    private fun setFormSimulasiKPR(hargaProperti: Long) {
        if (hargaProperti > 0) {
            val jumlahDP = hargaProperti.toPercent(20.0)
            val jumlahPinjaman = hargaProperti.toPercent()
            val defaultSukuBunga = listSukuBunga[0]
            val defaultSukuBungaFormated = "${sukuBungaFormat.format(defaultSukuBunga)}%"
            val defaultJangkaWaktuPinjaman = listJangkaWaktuPinjaman[0]
            val angsuranPerBulan = jumlahPinjaman
                .toAngsuranPerbulan(
                    defaultSukuBunga,
                    defaultJangkaWaktuPinjaman
                )

            binding.apply {
                edtHargaProperty.setText(hargaProperti.toRupiah())
                edtUangMuka.setText(jumlahDP.toRupiah())
                edtJangkaWaktuPinjaman.setText(defaultJangkaWaktuPinjaman.toString())
                edtSukuBunga.setText(defaultSukuBungaFormated)
                edtJumlahPinjaman.setText(jumlahPinjaman.toRupiah())
                tvAnguranPerbulan.text = angsuranPerBulan.toRupiah()
            }
        }
    }

    private fun setupEdtJangkaWaktuPinjaman(listJangkaWaktu: List<Int>) {
        val arrayAdapter = ArrayAdapter(this,
            R.layout.item_simulasi_kpr,
            R.id.tv_item_simulasi_kpr,
            listJangkaWaktu
        )
        binding.edtJangkaWaktuPinjaman.setAdapter(arrayAdapter)
    }

    private fun setupEdtSukuBunga(listSukuBunga: List<Double>) {
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.item_simulasi_kpr,
            R.id.tv_item_simulasi_kpr,
            listSukuBunga.toSukuBunganFormat()
        )
        binding.edtSukuBunga.setAdapter(arrayAdapter)
    }

    private fun BigDecimal.toAngsuranPerbulan(sukuBunga: Double, tenor: Int): BigDecimal {
        val cicilanPerhari = BigDecimal(this.toDouble() * (sukuBunga / 100)) / BigDecimal(tenor * 12)
        val cicilanPerbulan = (cicilanPerhari * BigDecimal(24)) - BigDecimal(950_140)
        return cicilanPerbulan
    }

    private fun Long.toPercent(percent: Double = 80.0): BigDecimal =
        BigDecimal((percent / 100) * toDouble())

    private fun List<Double>.toSukuBunganFormat(): List<String> =
        map {
            "${sukuBungaFormat.format(it)}%"
        }

    private fun <T> T.toRupiah(): String {
        val localeId = Locale("id", "ID")
        val doubleValue = when (this) {
            is Int -> toDouble()
            is BigDecimal -> toDouble()
            is Double -> this
            else -> 0.0
        }
        val numberFormat = NumberFormat.getCurrencyInstance(localeId).apply {
            minimumFractionDigits = 0
        }
        return numberFormat.format(doubleValue)
    }

    companion object {
        private val sukuBungaFormat = "%.2f"
    }
}
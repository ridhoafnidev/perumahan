package com.ridhoafnidev.project.feature.detail_perumahan

import android.widget.ArrayAdapter
import androidx.navigation.navArgs
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.ActivitySimulasiKprActivityBinding
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class SimulasiKPRActivity : BaseActivity<ActivitySimulasiKprActivityBinding>(ActivitySimulasiKprActivityBinding::inflate) {

    private val arguments: SimulasiKPRActivityArgs by navArgs()
    private val hargaProperti: Int
        get() = arguments.hargaProperti
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
        binding.btnSimulasi.setOnClickListener {
            val sukuBunga = binding.edtSukuBunga.text.toString().dropLast(1).toDouble()
            val defaultJangkaWaktuPinjaman = binding.edtJangkaWaktuPinjaman.text.toString().toInt()

            val angsuranPerBulan = jumlahPinjaman
                .toAngsuranPerbulan(
                    sukuBunga,
                    defaultJangkaWaktuPinjaman
                )

            binding.tvAnguranPerbulan.text = angsuranPerBulan.toRupiah()
        }
    }

    private fun setFormSimulasiKPR(hargaProperti: Int) {
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

    private fun setupEdtJangkaWaktuPinjaman(listJangkaWaktu: List<Int>) {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_simulasi_kpr, R.id.tv_item_simulasi_kpr, listJangkaWaktu)
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

    private fun Int.toPercent(percent: Double = 80.0): BigDecimal =
        BigDecimal((percent / 100) * toDouble())

    private fun List<Double>.toSukuBunganFormat(): List<String> =
        map {
            "${sukuBungaFormat.format(it)}%"
        }

    private fun <T> T.toRupiah(): String {
        val localeId = Locale("id", "ID")
        val doubleValue = when (this) {
            is Int -> toDouble()
            is Double -> this
            is BigDecimal -> toDouble()
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
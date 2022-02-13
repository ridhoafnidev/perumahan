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

    private val listJangkaWaktuPinjaman = (1..30).toList()
    private val listSukuBunga = listOf(
        6.00, 6.68, 7.70, 7.75, 8.00, 9.25, 9.75, 9.99, 10.29, 10.99, 11.29
    ).toSukuBunganFormat()

    override fun initView() {

        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_simulasi_kpr))

        setFormSimulasiKPR(arguments.hargaProperti)
        setupEdtJangkaWaktuPinjaman(listJangkaWaktuPinjaman)
        setupEdtSukuBunga(listSukuBunga)
    }

    override fun initListener() {

    }

    private fun setFormSimulasiKPR(hargaProperti: Int) {
        val jumlahDP = hargaProperti.toPercent(20.0)
        val jumlahPinjaman = hargaProperti.toPercent(80.0)
        val defaultSukuBunga = listSukuBunga[0]
//        val angsuranPerBulan = (jumlahPinjaman * defaultSukuBunga.toBigDecimal()) * (listJangkaWaktuPinjaman[0] / 120).toBigDecimal()

        binding.apply {
            edtHargaProperty.setText(hargaProperti.toRupiah())
            edtUangMuka.setText(jumlahDP.toRupiah())
            edtJangkaWaktuPinjaman.setText(listJangkaWaktuPinjaman[0].toString())
            edtSukuBunga.setText(defaultSukuBunga)
            edtJumlahPinjaman.setText(jumlahPinjaman.toRupiah())
//            tvAnguranPerbulan.text = angsuranPerBulan.toRupiah()
        }
    }

    private fun setupEdtJangkaWaktuPinjaman(listJangkaWaktu: List<Int>) {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_simulasi_kpr, R.id.tv_item_simulasi_kpr, listJangkaWaktu)
        binding.edtJangkaWaktuPinjaman.setAdapter(arrayAdapter)
    }

    private fun setupEdtSukuBunga(listSukuBunga: List<String>) {
        val arrayAdapter = ArrayAdapter(this, R.layout.item_simulasi_kpr, R.id.tv_item_simulasi_kpr, listSukuBunga)
        binding.edtSukuBunga.setAdapter(arrayAdapter)
    }

    private fun Int.toPercent(percent: Double): BigDecimal =
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
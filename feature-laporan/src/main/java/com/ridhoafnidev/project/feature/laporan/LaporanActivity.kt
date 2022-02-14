package com.ridhoafnidev.project.feature.laporan

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.util.Pair
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.google.android.material.datepicker.MaterialDatePicker
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.laporan.Laporan
import com.ridhoafnidev.project.core_domain.model.laporan.ListLaporan
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.laporan.databinding.ActivityLaporanBinding
import com.ridhoafnidev.project.feature.laporan.viewholder.ItemLaporanViewHolder
import com.ridhoafnidev.project.feature.laporan.viewmodel.LaporanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class LaporanActivity : BaseActivity<ActivityLaporanBinding>(ActivityLaporanBinding::inflate) {

    private val laporanViewModel: LaporanViewModel by viewModel()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val now = Calendar.getInstance().timeInMillis

    private val defaultStart = "2022-02-01 22:20:34"
    private val defaultEnd = dateFormat.format(now)

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_laporan))

        getAllLaporan()
        observeLaporan()
    }

    override fun initListener() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.laporan_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter_action) {
            showDateRangePicker()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDateRangePicker() {
        val dateRangePickerBuilder = MaterialDatePicker.Builder.dateRangePicker()
        dateRangePickerBuilder.setSelection(Pair(now, now))

        val dateRangePicker = dateRangePickerBuilder.build()
        dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())

        dateRangePicker.addOnPositiveButtonClickListener {
            laporanViewModel.getLaporan(
                dateFormat.format(it.first),
                dateFormat.format(it.second)
            )
        }
    }

    private fun getAllLaporan() {
        laporanViewModel.getLaporan(
            defaultStart,
            defaultEnd
        )
    }

    private fun observeLaporan() {
        laporanViewModel.listLaporan.observe(this) { apiEvent ->
            when (apiEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    setupRvLaporan(apiEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupRvLaporan(listLaporan: ListLaporan) {
        binding.lottieNotFound.visibility = if (listLaporan.isNotEmpty()) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }

        val dataSource = dataSourceTypedOf(listLaporan)
        binding.rvLaporan.setup {
            withDataSource(dataSource)
            withItem<Laporan, ItemLaporanViewHolder>(R.layout.item_laporan) {
                onBind(::ItemLaporanViewHolder) { _, item ->
                    tvNamaLengkap.text = item.nama
                    tvAlamat.text = item.alamat
                    tvPerumahan.text = item.perumahan
                    tvTipeRumah.text = item.tipeRumah
                    tvTanggalPengajuan.text = item.tanggalPengajuan
                    chipStatusPengajuan.text = item.statusPengajuan
                }
            }
        }
    }
}
package com.ridhoafnidev.project.feature.laporan.viewholder

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.google.android.material.chip.Chip
import com.ridhoafnidev.project.feature.laporan.R

class ItemLaporanViewHolder(view: View) : ViewHolder(view) {
    val tvNamaLengkap: TextView = view.findViewById(R.id.tv_nama_calon_pemilik)
    val tvAlamat: TextView = view.findViewById(R.id.tv_alamat_calon_pemilik)
    val tvPerumahan: TextView = view.findViewById(R.id.tv_perumahan_calon_pemilik)
    val tvTipeRumah: TextView = view.findViewById(R.id.tv_tipe_rumah_calon_pemilik)
    val tvTanggalPengajuan: TextView = view.findViewById(R.id.tv_tanggal_pengajuan)
    val chipStatusPengajuan: Chip = view.findViewById(R.id.chip_status_calon_pemilik)
}
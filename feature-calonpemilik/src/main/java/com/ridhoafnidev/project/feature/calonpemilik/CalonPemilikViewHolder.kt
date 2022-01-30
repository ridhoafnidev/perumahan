package com.ridhoafnidev.project.feature.calonpemilik

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.google.android.material.chip.Chip

class CalonPemilikViewHolder(view: View) : ViewHolder(view) {
    val tvNamaCalonPemilik: TextView = view.findViewById(R.id.tv_nama_calon_pemilik)
    val tvAlamatCalonPemilik: TextView = view.findViewById(R.id.tv_alamat_calon_pemilik)
    val tvTipeRumahCalonPemilik: TextView = view.findViewById(R.id.tv_tipe_rumah_calon_pemilik)
    val chipStatusCalonPemilik: Chip = view.findViewById(R.id.chip_status_calon_pemilik)
}
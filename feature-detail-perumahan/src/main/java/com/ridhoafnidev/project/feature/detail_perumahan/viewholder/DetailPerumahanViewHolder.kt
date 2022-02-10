package com.ridhoafnidev.project.feature.detail_perumahan.viewholder

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.ridhoafnidev.project.feature.detail_perumahan.R

class DetailPerumahanViewHolder(view: View) : ViewHolder(view) {
    val tbNamaPerumahan_: TextView = view.findViewById(R.id.tb_nama_perumahan_)
    val tbLuasTanahPerumahan: TextView = view.findViewById(R.id.tb_luas_tanah_perumahan)
    val tbAlamatPerumahan: TextView = view.findViewById(R.id.tb_alamat_perumahan)
    val tbKeteranganPerumahan: TextView = view.findViewById(R.id.tb_keterangan_perumahan)
}
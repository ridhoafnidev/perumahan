package com.ridhoafnidev.project.feature.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.ridhoafnidev.home.R

class ItemPerumahanViewHolder(view: View) : ViewHolder(view) {
    val ivPhotoPerumahan: ImageView = view.findViewById(R.id.iv_photo_perumahan)
    val tvNamaTipePerumahan: TextView = view.findViewById(R.id.tv_nama_tipe_perumahan)
    val tvUkuranPerumahan: TextView = view.findViewById(R.id.tv_ukuran_perumahan)
    val tvTotalPerumahan: TextView = view.findViewById(R.id.tv_total_perumahan)
    val tvHargaPerumahan: TextView = view.findViewById(R.id.tv_harga_perumahan)
}
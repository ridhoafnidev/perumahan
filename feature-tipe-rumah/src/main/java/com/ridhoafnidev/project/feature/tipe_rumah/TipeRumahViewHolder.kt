package com.ridhoafnidev.project.feature.tipe_rumah

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder

class TipeRumahViewHolder(view: View) : ViewHolder(view) {
    val tvNamaTipe = view.findViewById<TextView>(R.id.tv_nama_tipe)
    val tvUkuran = view.findViewById<TextView>(R.id.tv_ukuran)
    val btnEdit = view.findViewById<ImageButton>(R.id.btn_edit)
    val btnDelete = view.findViewById<ImageButton>(R.id.btn_delete)
}
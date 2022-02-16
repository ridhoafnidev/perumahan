package com.ridhoafnidev.project.feature.pengguna.viewholder

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.ridhoafnidev.project.feature.pengguna.R

class ItemPenggunaViewHolder(view: View) : ViewHolder(view) {
    val tvUsername: TextView = view.findViewById(R.id.tv_username)
    val tvRole: TextView = view.findViewById(R.id.tv_role)
}
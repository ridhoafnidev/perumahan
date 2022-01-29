package com.ridhoafnidev.project.feature.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.ridhoafnidev.home.R

class ItemCurrentEventViewHolder(view: View) : ViewHolder(view) {
    val titleEvent: TextView = view.findViewById(R.id.tv_title_event)
    val mounthEvent: TextView = view.findViewById(R.id.tv_mounth_event)
    val dateEvent: TextView = view.findViewById(R.id.tv_date_event)
    val placeEvent: TextView = view.findViewById(R.id.tv_place_event)
    val imageEvent: ImageView = view.findViewById(R.id.iv_event)
}
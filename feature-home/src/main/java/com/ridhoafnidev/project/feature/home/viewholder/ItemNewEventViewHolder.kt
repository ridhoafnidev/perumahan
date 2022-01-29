package com.ridhoafnidev.project.feature.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.ridhoafnidev.home.R

class ItemNewEventViewHolder(view: View) : ViewHolder(view) {
    val titleEvent: TextView = view.findViewById(R.id.tv_title_new_event)
    val timestampEvent: TextView = view.findViewById(R.id.tv_timestame_new_event)
    val placeEvent: TextView = view.findViewById(R.id.tv_place_new_event)
    val imageEvent: ImageView = view.findViewById(R.id.iv_event)
}
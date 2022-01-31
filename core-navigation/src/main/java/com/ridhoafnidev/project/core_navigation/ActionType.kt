package com.ridhoafnidev.project.core_navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ActionType : Parcelable {
    @Parcelize
    object Add : ActionType()
    @Parcelize
    object Detail : ActionType()
    @Parcelize
    object Edit : ActionType()
    @Parcelize
    object Delete : ActionType()
}
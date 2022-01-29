package com.ridhoafnidev.project.core_util

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar

internal class NoSwipe : BaseTransientBottomBar.Behavior() {
    override fun canSwipeDismissView(child: View):Boolean {
        return false
    }
}
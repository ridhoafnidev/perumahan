package com.ridhoafnidev.project.core_util

import android.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showAlertDialog(
    title: String,
    message: String?,
    action: () -> Unit
) {
    val alertBuilder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { _, _ ->
            action()
        }
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }
        .create()

    alertBuilder.show()
}
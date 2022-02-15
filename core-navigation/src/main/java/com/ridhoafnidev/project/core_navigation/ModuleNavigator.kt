package com.ridhoafnidev.project.core_navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

interface ModuleNavigator{

    fun <T> T.navigateToAuthActivity(
        finnishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Auth, finnishCurrent)
    }

    fun <T> T.navigateToAuthActivity(
        finnishCurrent: Boolean = false
    ) where T : AppCompatActivity, T : ModuleNavigator {
        startActivity(ActivityClassPath.Auth, finnishCurrent)
    }

    fun<T> T.navigateToHomeActivity(
        finishCurrent: Boolean = false
    ) where T :AppCompatActivity, T : ModuleNavigator {
        startActivity(ActivityClassPath.Home, finishCurrent)
    }

    fun<T> T.navigateToHomeActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Home, finishCurrent)
    }

    fun<T> T.navigateToPerumahanActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Perumahan, finishCurrent)
    }

    fun<T> T.navigateToCalonPembeliActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Pemilik, finishCurrent)
    }

    fun<T> T.navigateToTipeRumahActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.TipeRumah, finishCurrent)
    }

    fun<T> T.navigateToDetailPerumahActivity(
        extras: Bundle,
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(
            activityClassPath = ActivityClassPath.DetailPerumahan,
            extras = extras,
            finishCurrent = finishCurrent
        )
    }

    fun<T> T.navigateToPersyaratanActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Persyaratan, finishCurrent)
    }

    fun<T> T.navigateToLaporanActivity(
        finishCurrent: Boolean = false
    ) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.Laporan, finishCurrent)
    }

    fun<T> T.navigateToPerumahanActivity(
        finishCurrent: Boolean = false
    ) where T : AppCompatActivity, T : ModuleNavigator {
        startActivity(ActivityClassPath.Perumahan, finishCurrent)
    }


}

//region Extension functions to start activity in Activities list without parameter

private fun AppCompatActivity.startActivity(intent: Intent, finnishCurrent: Boolean) {
    startActivity(intent)
    if (finnishCurrent) finish()
//    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

private fun AppCompatActivity.startActivity(
    activityClassPath: ActivityClassPath,
    finnishCurrent: Boolean
) = startActivity(activityClassPath.getIntent(this), finnishCurrent)

private fun Fragment.startActivity(intent: Intent, finnishCurrent: Boolean) {
    startActivity(intent)
    if (finnishCurrent) activity?.finish()
//    activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

private fun Fragment.startActivity(
    activityClassPath: ActivityClassPath,
    finishCurrent: Boolean,
    extras: Bundle = bundleOf()
) =
    startActivity(
        activityClassPath
            .getIntent(requireContext())
            .putExtras(extras),
        finishCurrent
    )

//endregion
package com.ridhoafnidev.project.core_navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ridhoafnidev.core_navigation.R

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
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

private fun AppCompatActivity.startActivity(
    activityClassPath: ActivityClassPath,
    finnishCurrent: Boolean
) = startActivity(activityClassPath.getIntent(this), finnishCurrent)

private fun Fragment.startActivity(intent: Intent, finnishCurrent: Boolean) {
    startActivity(intent)
    if (finnishCurrent) activity?.finish()
    activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

private fun Fragment.startActivity(activityClassPath: ActivityClassPath, finnishCurrent: Boolean) =
    startActivity(activityClassPath.getIntent(requireContext()), finnishCurrent)

//endregion
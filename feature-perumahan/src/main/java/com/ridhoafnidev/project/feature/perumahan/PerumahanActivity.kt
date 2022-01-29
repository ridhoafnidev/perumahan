package com.ridhoafnidev.project.feature.perumahan

import androidx.navigation.findNavController
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.perumahan.databinding.ActivityCreateEventBinding

class PerumahanActivity : BaseActivity<ActivityCreateEventBinding>(ActivityCreateEventBinding::inflate) {

    private val navController by lazy { findNavController(R.id.event_navigation) }

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_create_perumahan))
    }

    override fun initListener() {
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}
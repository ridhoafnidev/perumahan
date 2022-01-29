package com.ridhoafnidev.project.feature.calonpemilik

import androidx.navigation.findNavController
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.calonpemilik.databinding.ActivityCalonPemilikBinding

class CalonPemilikActivity : BaseActivity<ActivityCalonPemilikBinding>(ActivityCalonPemilikBinding::inflate) {

    private val navController by lazy { findNavController(R.id.calon_pemilik_navigation) }

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_create_perumahan))
    }

    override fun initListener() {
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
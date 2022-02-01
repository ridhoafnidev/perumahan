package com.ridhoafnidev.project.feature.calonpemilik

import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.calonpemilik.databinding.ActivityCalonPemilikBinding

class CalonPemilikActivity : BaseActivity<ActivityCalonPemilikBinding>(ActivityCalonPemilikBinding::inflate) {

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_create_perumahan))
    }

    override fun initListener() {
    }
}
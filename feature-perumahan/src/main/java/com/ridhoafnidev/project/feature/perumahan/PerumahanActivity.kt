package com.ridhoafnidev.project.feature.perumahan

import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.perumahan.databinding.ActivityPerumahanBinding

class PerumahanActivity : BaseActivity<ActivityPerumahanBinding>(ActivityPerumahanBinding::inflate) {

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_perumahan))
    }

    override fun initListener() {
    }
}
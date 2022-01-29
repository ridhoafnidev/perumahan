package com.ridhoafnidev.project.feature.tipe_rumah

import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.tipe_rumah.databinding.ActivityTipeRumahBinding

class TipeRumahActivity : BaseActivity<ActivityTipeRumahBinding>(ActivityTipeRumahBinding::inflate) {
    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_tipe_rumah))
    }

    override fun initListener() {

    }
}
package com.ridhoafnidev.project.feature.detail_perumahan

import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.ActivityDetailPerumahanBinding

class DetailPerumahanActivity : BaseActivity<ActivityDetailPerumahanBinding>(ActivityDetailPerumahanBinding::inflate) {
    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_detail_perumahan))
    }

    override fun initListener() {
    }
}
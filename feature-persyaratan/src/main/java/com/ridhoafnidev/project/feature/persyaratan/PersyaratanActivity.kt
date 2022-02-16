package com.ridhoafnidev.project.feature.persyaratan

import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.persyaratan.databinding.ActivityPersyaratanBinding

class PersyaratanActivity : BaseActivity<ActivityPersyaratanBinding>(ActivityPersyaratanBinding::inflate) {

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_persyaratan))
    }

    override fun initListener() {

    }

}
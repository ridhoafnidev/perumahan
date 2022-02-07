package com.ridhoafnidev.project.feature.calonpemilik

import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.feature.calonpemilik.databinding.ActivityPreviewDpActivityBinding

class PreviewDPActivity : BaseActivity<ActivityPreviewDpActivityBinding>(ActivityPreviewDpActivityBinding::inflate) {

    private val args: PreviewDPActivityArgs by navArgs()
    private val photoUrl: String
        get() = args.photoUrl

    override fun initView() {
        Glide.with(application)
            .load(photoUrl)
            .into(binding.subDpPreview)
    }

    override fun initListener() {
        binding.btnClose.setOnClickListener {
            onBackPressed()
        }
    }

}